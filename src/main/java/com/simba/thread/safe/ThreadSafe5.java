package com.simba.thread.safe;

import java.util.TreeMap;

/**
 * 死锁的情况
 * 线程中互相拥有占有的资源
 */
public class ThreadSafe5 {

	Object object1 = new Object();
	Object object2 = new Object();
	public void a (){
		synchronized (object1){
			synchronized (object2){
				System.out.println("do...a...");
			}
		}
	}
	public void b (){
		synchronized (object2){
			synchronized (object1){
				System.out.println("do...b...");
			}
		}
	}

	public static void main(String[] args) {
		ThreadSafe5 threadSafe5 = new ThreadSafe5();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					System.out.println("---"+Thread.currentThread().getName()+"----");
					threadSafe5.a();
				}

			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					System.out.println("---"+Thread.currentThread().getName()+"----");
					threadSafe5.b();
				}

			}
		}).start();

	}
}