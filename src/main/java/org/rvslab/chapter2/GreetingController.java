package org.rvslab.chapter2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	@RequestMapping("/Hello")
	Greet greet() {
		return new Greet("Hello World!");
	}

}
