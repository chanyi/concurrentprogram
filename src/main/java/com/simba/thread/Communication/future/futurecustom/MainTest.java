package com.simba.thread.Communication.future.futurecustom;

public class MainTest {

	public static void main(String[] args) {
		//下单
		System.out.println("下单");
		ProductFactory productFactory = new ProductFactory();
		Future future =productFactory.createProduct("cake");
		//同时去做其他事情
		System.out.println("同时去做其他的事情");
		//来取蛋糕
		System.out.println("来取蛋糕"+future.getProduct());
	}
}
