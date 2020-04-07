package com.simba.thread.Communication.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test {

	Random random = new Random();

	public void meeting(CyclicBarrier cyclicBarrier){
		try {
			Thread.sleep(random.nextInt(4000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"到达会议室");
		if(Thread.currentThread().getName().equals("Thread-0")){
			throw new RuntimeException();
		}
		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName()+"发言");

	}

	public static void main(String[] args) {
		Test test =new Test();
		CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
			@Override
			public void run() {
				System.out.println("到齐了 开始开会");
			}
		});
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					test.meeting(cyclicBarrier);
				}
			}).start();
		}
	}
}
