package com.application.expensestracker.controller;

import com.application.expensestracker.exception.ApplicationExceptionHandler;
import com.application.expensestracker.exception.RequestValidationException;
import com.application.expensestracker.model.Expenses;
import com.application.expensestracker.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExpensesController {

    @Autowired
    ExpensesService expensesService;

    @GetMapping(path = "/expenses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Expenses>> get() {
        List<Expenses> expenses = expensesService.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
        return new ResponseEntity<List<Expenses>>(expenses,httpHeaders, HttpStatus.OK);
    }

    @PostMapping(path = "/expenses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Expenses> save(@RequestBody Expenses expenses){
        Expenses storedExpenses = expensesService.save(expenses);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
        return new ResponseEntity<Expenses>(httpHeaders, HttpStatus.OK);
    }

    @GetMapping(path = "/expenses/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Expenses> get(@PathVariable("id") Long id){
        Expenses expenses = expensesService.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
        return new ResponseEntity<Expenses>(expenses, httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping(path = "/expenses/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        expensesService.delete(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8")));
        return new ResponseEntity<String>("Expense is deleted successfully", HttpStatus.OK);
    }
}
