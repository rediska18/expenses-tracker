package com.application.expensestracker.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 @author Ярослав
 @date 05.03.2021
 @version 1.0
 */
@Entity
@Table(name="expenses")
public class Expenses {

    private Long id;
    private String expenses;
    private String description;
    private BigDecimal amount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expenses expenses = (Expenses) o;
        return id.equals(expenses.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "id=" + id +
                ", expenses='" + expenses + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
