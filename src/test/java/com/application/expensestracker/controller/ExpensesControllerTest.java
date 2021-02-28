package com.application.expensestracker.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ExpensesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = "classpath:db/h2/test-data.dml")
    void get() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(MockMvcRequestBuilders
                .get(ExpensesController.ROOT_URL + "/expenses")
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                    mockHttpServletResponse.getHeaderValue(HttpHeaders.CONTENT_TYPE).equals(new StringBuilder().append(MediaType.APPLICATION_JSON_VALUE).append(";charset=").append(Charset.forName("UTF-8")).toString());
                    mockHttpServletResponse.getContentAsString().contains("{\"id\":1,\"expenses\":\"Not healthy food\",\"description\":\"alcohol\",\"amount\":300.00}");
                })
                .andReturn();
    }

    @Test
    void save() {
    }

    @Test
    void testGet() {
    }

    @Test
    void delete() {
    }
}