package com.anagram.serverless.springboot.test;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import junit.framework.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Before
    public void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

	
	@Test
	public void retrieveAllAnagramsSuccess() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/anagrams").accept(
				MediaType.APPLICATION_JSON).param("input", "abc");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String expected = "[abc,acb,bac,bca,cab,cba]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
		Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeader(HttpHeaders.CONTENT_TYPE));
		
		Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());
		
	}
	
	@Test
	public void retrieveAllAnagramsError() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/anagrams").accept(
				MediaType.APPLICATION_JSON).param("badinput", "");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(),
				response.getStatus());
		
	}

}
