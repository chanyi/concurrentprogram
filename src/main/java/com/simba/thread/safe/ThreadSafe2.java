package com.simba.thread.safe;

import java.util.PrimitiveIterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafe2 {
	public static void main(String[] args) {
//		int i = 0;
//		while (i < 1000) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
////					try {
////						Thread.sleep(1000);
////					} catch (InterruptedException e) {
////						e.printStackTrace();
////					}
//					LazySingleModel lazySingleModel = LazySingleModel.instance();
//					System.out.println(Thread.currentThread().getName() + "lazysingle:" + lazySingleModel);
//				}
//			}).start();
//			i++;
//		}

		Executor executor = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 100 ; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					LazySingleModel lazySingleModel = null;
					try {
						lazySingleModel = LazySingleModel.instance();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+":"+lazySingleModel);
				}
			});
		}
		((ExecutorService) executor).shutdown();


	}
}
