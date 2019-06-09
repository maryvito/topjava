package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.meals.MealDao;
import ru.javawebinar.topjava.dao.meals.MealMapDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static String MEALS = "/meals.jsp";
    private static String INSERT_OR_EDIT = "/editMeal.jsp";
    private static String LIST_MEALS = "/listMeals.jsp";
    private MealDao dao;

    public MealServlet() {
        super();
        dao = new MealMapDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.removeMeal(mealId);
            forward = LIST_MEALS;
            request.setAttribute("mealTos",
                    MealsUtil.convertToMealsTo(dao.getAllMeals()));
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            Integer mealId = Integer.parseInt(request.getParameter("mealId"));
            MealTo mealTo = MealsUtil.createWithExcess700(dao.getMeal(mealId)) ;
            request.setAttribute("mealTo", mealTo);
        } else if (action.equalsIgnoreCase("listMeals")){
            forward = LIST_MEALS;
            request.setAttribute("mealTos",
                    MealsUtil.convertToMealsTo(dao.getAllMeals()));
        } else {
            forward = INSERT_OR_EDIT;
        }


        request.setCharacterEncoding("UTF-8");
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Meal meal = new Meal();
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        meal.setDescription(request.getParameter("description"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"), formatter);
        meal.setDateTime(dateTime);

        String mealId = request.getParameter("mealId");
        if(mealId == null || mealId.isEmpty())
        {
            dao.createMeal(meal);
        }
        else
        {
            meal.setId(Integer.parseInt(mealId));
            dao.updateMeal(meal);
        }

        request.setCharacterEncoding("UTF-8");
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEALS);
        request.setAttribute("mealTos", MealsUtil.convertToMealsTo(dao.getAllMeals()));
        view.forward(request, response);
    }
}
