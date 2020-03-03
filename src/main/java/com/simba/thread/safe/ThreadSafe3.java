package com.simba.thread.safe;

/**
 * 可重入锁
 * 如果线程已经抢到了锁（类对象），那么就可以直接调用两个synchronized，不回在等待
 * 就是说可以将两个synchronized锁看成是一个锁
 *
 */
public class ThreadSafe3 {
	public synchronized void a(){
		System.out.println("a...");
		b();
	}

	public synchronized void b(){
		System.out.println("b...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//这里如果是同一个对象，两个线程的进入两个方法是按照锁的串行顺序去执行的
		//如果不是同一个对象，那么就是两个锁，两个方法的执行顺序是并行的，并不是串行执行
		ThreadSafe3 threadSafe3 = new ThreadSafe3();
		ThreadSafe3 threadSafe31 = new ThreadSafe3();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				threadSafe3.b();
			}
		}).start();


		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				threadSafe31.a();
			}
		}).start();


	}
}
