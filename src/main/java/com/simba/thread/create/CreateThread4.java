package com.simba.thread.create;


import sun.plugin2.jvm.RemoteJVMLauncher;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建带有返回值的线程
 *
 * 创建带有返回整数的线程
 */
public class CreateThread4 implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		System.out.println("正在执行线程。。。。");
		System.out.println("正在线程计算。。。。");
		Thread.sleep(2000);
		return 1;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CreateThread4  createThread4 = new CreateThread4();
		FutureTask<Integer> task = new FutureTask<Integer>(createThread4);
		Thread thread = new Thread(task);
		thread.start();
		System.out.println("thread start ...");

		Integer returnTask = task.get();
		System.out.println("thread return back :"+returnTask);
	}
}
