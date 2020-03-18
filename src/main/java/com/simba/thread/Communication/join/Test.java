package com.simba.thread.Communication.join;

public class Test {


	public void a (Thread joinThread){
		System.out.println("method a start ...");
		joinThread.start();
		try {
			joinThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method a end ...");
	}

	public void b(){
		System.out.println("method b start ...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method b end ...");
	}

	/**
	 * 等待B方法执行完成之后，再执行A方法
	 * @param args
	 */
	public static void main(String[] args) {
		Test test = new Test();

		Thread thread =new Thread(new Runnable() {
			@Override
			public void run() {
				test.b();
			}
		});

		new Thread(new Runnable() {
			@Override
			public void run() {
				test.a(thread);
			}
		}).start();
	}

}
