package com.simba.thread.Communication.countdownlatch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {

	private int [] nums;

	public Test(int lines){

		this.nums = new int[lines];

	}

	private void calc(String line,int index){
		System.out.println(Thread.currentThread().getName()+"start----"+line);
		String[] strnums = line.split(",");
		int total = 0;
		for (int i = 0; i < strnums.length ; i++) {
			total += Integer.parseInt(strnums[i]);
		}
		nums[index] = total;
		System.out.println(Thread.currentThread().getName()+"end----"+line);
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
			 bufferedReader = new BufferedReader(new FileReader("/Users/lilei/Downloads/nums.txt"));
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
		Test test = new Test(lineCount);
		for (int i = 0; i < lineCount; i++){
			final int j = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					test.calc(list.get(j),j);
				}
			}).start();
		}

		//这样的代码要避免写，因为此时idea中activeCount为2，eclipse中为1
		while(Thread.activeCount()>2){

		}

		test.sum();



	}


}
