package org.kzk.springSample.pojo;

public class HelloWorldImpl implements IHelloWorld {
	
	private String name;
	
	public HelloWorldImpl() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNae() {
		return name;
	}

	public void helloWorld() {
		System.out.println("Hello " + this.name + "!");
	}

}
