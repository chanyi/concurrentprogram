package com.simba.thread.Communication.threadlocal;

import com.simba.thread.NewThread;

import java.util.TreeMap;

public class Test {

	/**
	 * 每个线程所独占的变量，具有线程安全的特性
	 */
	private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
			return new Integer(0);
		}
	};


	public int getNext(){
		Integer value = threadLocal.get();
		value++;
		threadLocal.set(value);
		return value;
	}

	public static void main(String[] args) {
		Test test = new Test();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					System.out.println(Thread.currentThread().getName()+"--"+test.getNext());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					System.out.println(Thread.currentThread().getName()+"--"+test.getNext());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

}
