package org.rvslab.chapter2;

import org.springframework.hateoas.ResourceSupport;

public class Greet extends ResourceSupport{
	private String message;
	Greet(){
		
	}
	public Greet(String message)
	{
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
