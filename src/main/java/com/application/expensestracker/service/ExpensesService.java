package com.application.expensestracker.service;

import com.application.expensestracker.model.Expenses;
import com.application.expensestracker.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

/**
 @author Ярослав
 @date 05.03.2021
 @version 1.0
 */
@Service
public class ExpensesService {

    @Autowired
    ExpensesRepository expensesRepository;

    public List<Expenses> findAll() {
        return expensesRepository.findAll();
    }

    public Expenses save(Expenses expenses) {
        Expenses storedExpenses = expensesRepository.save(expenses);
        return storedExpenses;
    }

    public Expenses findById(Long id) {
        Optional<Expenses> expenses = expensesRepository.findById(id);
        if (expenses.isPresent()) {
            return expenses.get();
        }
        return null;
    }

    public void delete(Long id) {
        Expenses expenses = findById(id);
        expensesRepository.delete(expenses);
    }
}
