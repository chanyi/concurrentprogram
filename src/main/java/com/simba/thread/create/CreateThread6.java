package com.simba.thread.create;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池创建一个线程
 */
public class CreateThread6 {

	public static void main(String[] args) {
		//创建一个有是个线程的线程池
//		Executor threadPool = Executors.newFixedThreadPool(10);
		Executor threadPool = Executors.newCachedThreadPool();

		//newFixedThreadPool提交了100个线程任务，但是由这10个线程来执行
		//newCachedThreadPool提交了线程任务就创建线程执行，认为不够用就创建，够用就回收
		for(int i=0 ;i<100 ;i++){
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程"+Thread.currentThread().getName()+"创建，执行。。。");
				}
			});
		}
		//线程创建之后不销毁的话，线程池中的线程会一直存在
		((ExecutorService) threadPool).shutdown();

	}

}
