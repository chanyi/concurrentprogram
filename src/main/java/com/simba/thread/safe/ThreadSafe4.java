package com.simba.thread.safe;

import javax.sound.midi.Soundbank;
import java.util.Random;

/**
 * 自旋锁的实现
 */
public class ThreadSafe4 {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"do..");
				try {
					Thread.sleep(new Random().nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"finish..");
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"do..");
				try {
					Thread.sleep(new Random().nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"finish..");
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"do..");
				try {
					Thread.sleep(new Random().nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"finish..");
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"do..");
				try {
					Thread.sleep(new Random().nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"finish..");
			}
		}).start();


		//此时线程除了以上生成的线程外 还有两个线程，一个是main线程一个是Monitor Ctrl-Break线程
		//其中的Monitor ctrl-break线程是IntelliJ IDEA执行用户代码的时候，
		// 实际是通过反射方式去调用，而与此同时会创建一个Monitor Ctrl-Break 用于监控目的
		//其他的编译器不存在。所以代码中不能通过这样的方式实现自旋
		while(Thread.activeCount() != 1 ){
			//内部自旋，直到所有的线程都执行完
//			System.out.println("waiting...");
			System.out.println("000");
			//获取当前所有的线程
			Thread.currentThread().getThreadGroup().list();
			System.out.println("000");
			ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
			//获取根线程
			ThreadGroup topGroup = threadGroup;
			if(threadGroup!=null){
				topGroup = threadGroup;
				threadGroup= threadGroup.getParent();
				System.out.println("topGroup..."+topGroup.getName());
				int count2 = topGroup.activeCount()*2;
				Thread[] threads = new Thread[count2];
				topGroup.enumerate(threads);
				for (int i = 0; i <threads.length ; i++) {
					if(threads[i] != null){
						System.out.println("---"+threads[i].getName());
					}
				}
			}

			System.out.println("waiting..."+Thread.activeCount()+".."+threadGroup.getName());
		}

		System.out.println("所有线程都执行完");
	}


}
