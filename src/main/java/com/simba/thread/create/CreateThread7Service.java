package com.simba.thread.create;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CreateThread7Service {

	//开启一个异步线程
	@Async
	public void doThread1(){
		while (true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("do thread1 ...");
		}
	}

	@Async
	public void doThread2(){
		while (true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("do thread2 ...");
		}
	}
}
