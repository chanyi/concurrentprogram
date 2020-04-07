package com.simba.thread.Communication.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 信号量，控制资源的引用
 */
public class Test {

	private static volatile int count = 0;

	public void method(Semaphore semaphore){
		try {
			//获取许可
			semaphore.acquire();
			count ++;
			System.out.println(Thread.currentThread().getName()+"开始执行之后，当前count = "+ count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//模拟执行时间
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//释放许可
		semaphore.release();
		count--;
		System.out.println(Thread.currentThread().getName()+"开始释放之后的，当前的count = "+ count);
	}

	public static void main(String[] args) {
		Test test = new Test();
		Semaphore semaphore =new Semaphore(5);
		for (int i = 0; i < 20; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					test.method(semaphore);
				}
			}).start();

		}
	}
}
