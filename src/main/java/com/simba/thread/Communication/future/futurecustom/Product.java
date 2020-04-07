package com.simba.thread.Communication.future.futurecustom;

public class Product {
	private int id;
	private String name;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product(){
	}

	public Product(int id, String name){
		System.out.println("开始生产蛋糕。。预计需要4s");
		this.id = id;
		this.name = name;
		try {
			Thread.sleep(4000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("蛋糕生产完毕");

	}

}
