package com.simba.thread.create;

/**
 * 通过实现runnable接口创建一个线程
 * 作为一个线程任务存在
 *
 */
public class CreateThread2 implements Runnable{


	@Override
	public void run() {
		System.out.println("thread go....");
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new CreateThread2());

		//thread 调用start方法 其实就是在调用接口的run方法

		thread.start();
	}
}
