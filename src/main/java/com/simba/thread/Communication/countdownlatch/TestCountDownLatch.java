package com.simba.thread.Communication.countdownlatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

	private int [] nums;

	public TestCountDownLatch(int lines){

		this.nums = new int[lines];

	}

	private void calc(String line,int index,CountDownLatch countDownLatch){
		System.out.println(Thread.currentThread().getName()+"start----"+line);
		String[] strnums = line.split(",");
		int total = 0;
		for (int i = 0; i < strnums.length ; i++) {
			total += Integer.parseInt(strnums[i]);
		}
		nums[index] = total;
		System.out.println(Thread.currentThread().getName()+"end----"+line);
		//每次执行完成之后 数量减一
		countDownLatch.countDown();
	}

	public void sum(){
		int total = 0;
		for (int i = 0; i < nums.length; i++) {
			total+=nums[i];
		}
		System.out.println("total = "+total);
	}

	public static List<String> readFile(){
		List<String> list = new ArrayList<>();
		String line = "";
		BufferedReader bufferedReader = null;
		try {
			 bufferedReader = new BufferedReader(new FileReader("/Users/lilei/myProject/concurrentprogram/src/main/java/com/simba/thread/Communication/countdownlatch/nums"));
			while ((line = bufferedReader.readLine()) != null){
				list.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static void main(String[] args) {
		List<String> list = readFile();
		int lineCount = list.size();
		CountDownLatch countDownLatch = new CountDownLatch(lineCount);
		TestCountDownLatch test = new TestCountDownLatch(lineCount);
		for (int i = 0; i < lineCount; i++){
			final int j = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					test.calc(list.get(j),j,countDownLatch);
				}
			}).start();
		}

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test.sum();



	}


}
