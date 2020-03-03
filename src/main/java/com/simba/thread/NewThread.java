package com.simba.thread;

public class NewThread implements Runnable{

	@Override
	public void run() {
		//当线程抢到资源之后自动调用此方法
		System.out.println("start thread ...");
		while (true){
			System.out.println("start thread while...");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {

		// create thread and create thread task
		Thread thread = new Thread(new NewThread());
		//thread start
		thread.start();
		while (true){
			System.out.println("main thread start");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}



	}
}
