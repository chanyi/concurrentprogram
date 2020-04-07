package com.simba.thread.Communication.future;

import sun.net.www.protocol.file.FileURLConnection;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {

	public static void main(String[] args) {

		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("正在执行。。。");
				Thread.sleep(2000);
				return 1;
			}
		};

		FutureTask<Integer> futureTask = new FutureTask(callable);
		new Thread(futureTask).start();

		try {
			Integer result = futureTask.get();
			System.out.println("获取到结果："+ result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}


	}
}
