package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException {
        boolean result = repository.delete(userId, id);
        if(!result)
            throw new NotFoundException("Еда не найдена или не принадлежит пользователю");
    }

    @Override
    public Meal get(int userId, int id) throws NotFoundException {
        Meal meal = repository.get(userId, id);
        if(meal == null)
            throw new NotFoundException("Еда не найдена или не принадлежит пользователю");
        return meal;
    }



    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId).stream().collect(Collectors.toList());
    }
}