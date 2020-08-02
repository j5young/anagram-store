package com.anagram.serverless.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@EnableWebMvc
public class AnagramController {
	
    private List<String> anagrams = new ArrayList<String>(); 
       

	  @RequestMapping(path = "/anagrams", method = RequestMethod.GET)
	  public List<String> getAllAnagrams(@RequestParam(value = "input",required=true) String input) {
		  this.makeAnagram(input.toCharArray(),0);
		  return anagrams;
	  }
	  
	  private void makeAnagram(char[] input, int i) {

			if (i == input.length-1 ) {
				anagrams.add(String.valueOf(input));
			}
			else {
				for (int j=i; j< input.length; j++) {
					char c = input[i]; 
					input[i] = input[j]; 
					input[j] = c;
					makeAnagram(input, i+1);
					c = input[i]; 
					input[i] = input[j]; 
					input[j] = c;
				}
			}
		}

}
