package com.application.expensestracker.controller;

import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.util.MimeType;
import java.net.URI;
import java.nio.charset.Charset;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ExpensesControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @Test
    @Sql(scripts = "classpath:db/h2/test-data.dml")
    void testGetMethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(new URI(ExpensesController.ROOT_URL + "/expenses"))
                //.accept(new MediaType("application", "json", Charset.forName("UTF-8"))))
                .headers(prepareHttpRequestHeaders(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                    mockHttpServletResponse.getHeaderValue(HttpHeaders.CONTENT_TYPE).equals(DataTypes.APPLICATION_JSON_UTF8);
                    mockHttpServletResponse.getContentAsString().contains("{\"id\":2,\"expenses\":\"Not healthy food\",\"description\":\"alcohol\",\"amount\":300.00}");
                })
                .andReturn();
    }

    @Test
    @Sql(scripts = "classpath:db/h2/test-data.dml")
    void testGetMethodWithParams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(new URI(ExpensesController.ROOT_URL + "/expenses/2"))
                //.accept(new MediaType("application", "json", Charset.forName("UTF-8"))))
                .headers(prepareHttpRequestHeaders(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                    mockHttpServletResponse.getHeaderValue(HttpHeaders.CONTENT_TYPE).equals(DataTypes.APPLICATION_JSON_UTF8);
                    mockHttpServletResponse.getContentAsString().contains("{\"id\":2,\"expenses\":\"Not healthy food\",\"description\":\"alcohol\",\"amount\":300.00}");
                })
                .andReturn();
    }

    @Test
    void testSaveMethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(new URI(ExpensesController.ROOT_URL + "/expenses"))
                //.accept(new MimeType("application", "json", Charset.forName("UTF-8")))
                .accept(DataTypes.APPLICATION_JSON_UTF8)
                //.headers(prepareHttpRequestHeaders(MediaType.APPLICATION_JSON))
                .content("{\"expenses\":\"Literature\",\"description\":\"magazines\",\"amount\":100.00}".getBytes(Charset.forName("UTF-8"))))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                    mockHttpServletResponse.getHeaderValue(HttpHeaders.CONTENT_TYPE).equals(DataTypes.APPLICATION_JSON_UTF8);
                })
                .andReturn();
    }

    @Test
    @Sql(scripts = "classpath:db/h2/test-data.dml")
    void testDeleteMethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete(new URI(ExpensesController.ROOT_URL + "/expenses/2"))
                .headers(prepareHttpRequestHeaders(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                    mockHttpServletResponse.getHeaderValue(HttpHeaders.CONTENT_TYPE).equals(DataTypes.TEXT_PLAIN_UTF8);
                    mockHttpServletResponse.getContentAsString().equals("TODO check.");
                })
                .andReturn();
    }

    private HttpHeaders prepareHttpRequestHeaders(MediaType mediaType){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(mediaType, Charset.forName("UTF-8")));
        return httpHeaders;
    }
}