package com.simba.thread.Communication.locksupport;

import com.simba.thread.NewThread;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 两个线程交替打印数组
 */
public class Test {
	static Thread thread1=null;
	static Thread thread2 = null;
	public static void main(String[] args) {
		char[] chars1 = "12345".toCharArray();
		char[] chars2 = "abcde".toCharArray();
		thread1 = new Thread(()->{
			for(char c : chars1){
				System.out.println(c);
				LockSupport.unpark(thread2);
				LockSupport.park();
			}

		});

		thread2 = new Thread(()->{
			for(char c : chars2){
				//park 信号量设置为0，线程不可以执行
				LockSupport.park();
				System.out.println(c);
				//park 信号量设置为1，线程可以执行
				LockSupport.unpark(thread1);
			}

		});

		thread1.start();
		thread2.start();

	}


}
