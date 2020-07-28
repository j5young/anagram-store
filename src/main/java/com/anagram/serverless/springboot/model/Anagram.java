package com.anagram.serverless.springboot.model;

import java.util.ArrayList;
import java.util.List;

public class Anagram {
	
	private String input;
	private List<String> anagrams = new ArrayList<String>();
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public List<String> getAnagrams() {
		return anagrams;
	}
	public void setAnagrams(List<String> anagrams) {
		this.anagrams = anagrams;
	}
	public Anagram(String input, List<String> anagrams) {
		super();
		this.input = input;
		this.anagrams = anagrams;
	}
	public Anagram(String input) {
		super();
		this.input = input;
	}
	

}
