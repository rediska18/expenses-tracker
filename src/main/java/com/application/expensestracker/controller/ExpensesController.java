package com.application.expensestracker.controller;

import com.application.expensestracker.model.Expenses;
import com.application.expensestracker.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping(ExpensesController.ROOT_URL)
public class ExpensesController {
    public static final String ROOT_URL = "/api/v1";

    @Autowired
    ExpensesService expensesService;

    @GetMapping(path = "/expenses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Expenses>> get() {
        List<Expenses> expenses = expensesService.findAll();
        return new ResponseEntity<List<Expenses>>(expenses, prepareHttpResponseHeaders(MediaType.APPLICATION_JSON), HttpStatus.OK);
    }

    @GetMapping(path = "/expenses/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Expenses> get(@PathVariable("id") Long id){
        Expenses expenses = expensesService.findById(id);
        return new ResponseEntity<Expenses>(expenses, prepareHttpResponseHeaders(MediaType.APPLICATION_JSON), HttpStatus.OK);
    }

    @PostMapping(path = "/expenses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Expenses> save(@RequestBody Expenses expenses){
        Expenses storedExpenses = expensesService.save(expenses);
        return new ResponseEntity<Expenses>(prepareHttpResponseHeaders(MediaType.APPLICATION_JSON), HttpStatus.OK);
    }

    @DeleteMapping(path = "/expenses/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        expensesService.delete(id);
        // TODO Не видаляти запис Expense. Уточнити, зберегти історію цін і оновити ціну у відповідності з кон'юктурою ринку
        return new ResponseEntity<String>("TODO check.",prepareHttpResponseHeaders(MediaType.TEXT_PLAIN), HttpStatus.OK);
    }

    private HttpHeaders prepareHttpResponseHeaders(MediaType mediaType){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(mediaType, Charset.forName("UTF-8")));
        return httpHeaders;
    }
}
