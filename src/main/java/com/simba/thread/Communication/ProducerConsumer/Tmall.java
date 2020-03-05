package com.simba.thread.Communication.ProducerConsumer;

public class Tmall {

	private int count;

	private final int MAX_COUNT = 10;

	public synchronized void push(){
		while (count > MAX_COUNT){
			try {
				System.out.println(Thread.currentThread().getName()+"库存已到上线。。。");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count++;
		System.out.println(Thread.currentThread().getName()+"已经生产："+count);
		notifyAll();
	}

	public synchronized void take(){
		while (count<=0){
			try {
				System.out.println(Thread.currentThread().getName()+"已经消费完。。。");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		count--;
		System.out.println(Thread.currentThread().getName()+"已经消费，剩余："+count);
		notifyAll();
	}
}
