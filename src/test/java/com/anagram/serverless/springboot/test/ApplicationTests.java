package com.anagram.serverless.springboot.test;

import org.junit.Before;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

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
import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	public ApplicationTests() {
		
	}
	
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
		//String expected = "[airmiles]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
		Assert.assertEquals(MediaType.APPLICATION_JSON+";charset=UTF-8", response.getHeader(HttpHeaders.CONTENT_TYPE));
		
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
