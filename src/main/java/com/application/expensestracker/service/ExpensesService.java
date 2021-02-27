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

@Service
public class ExpensesService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    ExpensesRepository expensesRepository;

    @Resource(name = "dataSource")
    public void setJdbcTemplate(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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
