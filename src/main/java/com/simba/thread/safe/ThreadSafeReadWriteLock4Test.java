package com.simba.thread.safe;

public class ThreadSafeReadWriteLock4Test {

	public static void main(String[] args) {

		ThreadSafeReadWriteLock threadSafeReadWriteLock = new ThreadSafeReadWriteLock();



		new Thread(new Runnable() {
			@Override
			public void run() {

				threadSafeReadWriteLock.put("1",1);

			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {

				threadSafeReadWriteLock.put("2",1);

			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {

				threadSafeReadWriteLock.put("3",1);


			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {

				threadSafeReadWriteLock.get("1");


			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {

				threadSafeReadWriteLock.get("2");


			}
		}).start();
	}
}
