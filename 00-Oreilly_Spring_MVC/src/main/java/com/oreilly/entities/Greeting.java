package com.oreilly.entities;

import java.util.Objects;

public class Greeting {

	 private String greeting;

	    public Greeting() {}  // used for de-serialization

	    public Greeting(String greeting) {
	        this.greeting = greeting;
	    }

	    public String getGreeting() {
	        return greeting;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Greeting)) return false;
	        Greeting gr = (Greeting) o;
	        return Objects.equals(greeting, gr.greeting);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(greeting);
	    }

	    @Override
	    public String toString() {
	        return greeting;
	    }
}
