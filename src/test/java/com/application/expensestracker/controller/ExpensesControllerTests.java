package com.application.expensestracker.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeType;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.nio.charset.Charset;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 @author Ярослав
 @date 06.03.2021
 @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
class ExpensesControllerTests {
    public static final String DOCUMENTATION_OUTPUT_FOLDER = "expenses";

    @RegisterExtension
    public RestDocumentationExtension restDocumentationExtension = new RestDocumentationExtension(DOCUMENTATION_OUTPUT_FOLDER);

    private StringBuilder requestUrlBuilder;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider){
        this.requestUrlBuilder = new StringBuilder(ExpensesController.ROOT_URL).append("/expenses");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .build();
    }

    @Test
    @Sql(scripts = "classpath:db/h2/test-data.dml")
    public void testGetMethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(new URI(requestUrlBuilder.toString()))
                //.accept(new MediaType("application", "json", Charset.forName("UTF-8"))))
                .headers(prepareHttpRequestHeaders(MediaType.APPLICATION_JSON)))
                .andDo(document(requestUrlBuilder.toString()))
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
    public void testGetMethodWithParams() throws Exception {
        requestUrlBuilder.append("/2");
        mockMvc.perform(MockMvcRequestBuilders
                .get(new URI(requestUrlBuilder.toString()))
                //.accept(new MediaType("application", "json", Charset.forName("UTF-8"))))
                //.headers(prepareHttpRequestHeaders(MediaType.APPLICATION_JSON)))
                .accept(DataTypes.APPLICATION_JSON_UTF8))
                .andDo(document(requestUrlBuilder.toString()))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                    mockHttpServletResponse.getHeaderValue(HttpHeaders.CONTENT_TYPE).equals(DataTypes.APPLICATION_JSON_UTF8);
                    mockHttpServletResponse.getContentAsString().contains("{\"id\":2,\"expenses\":\"Not healthy food\",\"description\":\"alcohol\",\"amount\":300.00}");
                })
                .andReturn();
    }

    @Test
    public void testSaveMethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(new URI(requestUrlBuilder.toString()))
                //.accept(new MediaType("application", "json", Charset.forName("UTF-8")))
                //.accept(DataTypes.APPLICATION_JSON_UTF8)
                .headers(prepareHttpRequestHeaders(MediaType.APPLICATION_JSON))
                .content("{\"expenses\":\"Literature\",\"description\":\"magazines\",\"amount\":100.00}".getBytes(Charset.forName("UTF-8"))))
                .andDo(document(requestUrlBuilder.toString()))
                .andExpect(status().isOk())
                /*
                .andDo(mvcResult -> {
                    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                    mockHttpServletResponse.getHeaderValue(HttpHeaders.CONTENT_TYPE).equals(DataTypes.APPLICATION_JSON_UTF8);
                })*/
                .andReturn();
    }

    @Test
    @Sql(scripts = "classpath:db/h2/test-data.dml")
    public void testDeleteMethod() throws Exception {
        requestUrlBuilder.append("/2");
        mockMvc.perform(MockMvcRequestBuilders
                .delete(new URI(requestUrlBuilder.toString()))
                .headers(prepareHttpRequestHeaders(MediaType.APPLICATION_JSON)))
                .andDo(document(requestUrlBuilder.toString()))
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