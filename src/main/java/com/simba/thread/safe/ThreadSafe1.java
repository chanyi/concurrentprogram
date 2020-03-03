package com.simba.thread.safe;

public class ThreadSafe1 {

	public static void main(String[] args) {
		Counter counter = new Counter();
		//单线程执行没有问题
//		while(true){
//			System.out.println("count:"+counter.getNext());
//		}

		//多线程执行会出现线程问题
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+":"+counter.getNext());
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+":"+counter.getNext());
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+":"+counter.getNext());
				}
			}
		}).start();
	}
}
