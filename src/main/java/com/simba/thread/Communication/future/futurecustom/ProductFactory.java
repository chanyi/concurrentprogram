package com.simba.thread.Communication.future.futurecustom;

import java.util.Random;

public class ProductFactory {

	public Future createProduct(String name){
		//订单类
		Future future = new Future();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Product product = new Product(new Random().nextInt(),name);
				future.setProduct(product);
			}
		}).start();
		return future;
	}


}
