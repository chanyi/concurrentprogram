package com.simba.thread.concurrentset.blockqueue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Tmall implements Shop {


	private int MAX_COUNT = 10;

	private BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(MAX_COUNT);



	public void push(){
		try {
			blockingQueue.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void take(){
		try {
			blockingQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void size(){
		System.out.println("queue size = "+ blockingQueue.size());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
