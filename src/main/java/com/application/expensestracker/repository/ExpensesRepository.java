package com.application.expensestracker.repository;

import com.application.expensestracker.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 @author Ярослав
 @date 05.03.2021
 @version 1.0
 */
@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

}
