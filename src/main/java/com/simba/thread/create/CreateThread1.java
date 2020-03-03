package com.simba.thread.create;

import com.simba.thread.CreateThread;

/**
 * 通过继承Thread类创建一个线程
 * 重写run方法
 */
public class CreateThread1 extends Thread{

	private static boolean flag = true;

	public CreateThread1(String name){
		super(name);
	}
	@Override
	public void run() {
		//自定义run方法内部
//		System.out.println("run :: "+getName()+"is interrupt:"+isInterrupted());
		System.out.println("run :: "+this.getName()+"is interrupt:"+this.isInterrupted());

		while (!this.isInterrupted()){
//		while (flag){
//			System.out.println("run :: "+getName()+"is interrupt:"+isInterrupted());
			System.out.println(getName()+"线程执行了");
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("Exception ++ run :: "+getName()+"is interrupt:"+isInterrupted());
				System.out.println(getName()+"线程interrupt:"+e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		CreateThread1 thread1 = new CreateThread1("11");
		CreateThread1 thread2 = new CreateThread1("22");
		CreateThread1 thread3 = new CreateThread1("33");
		CreateThread1 thread4 = new CreateThread1("44");

		//设置线程为守护线程，一个进程中如果只存在一个守护线程，那么这个守护线程也会死亡
		//如果有两个几多个非守护进程，那么守护进程还是会继续存在
//		thread1.setDaemon(true);
//		thread2.setDaemon(true);

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

//		flag = false;
		thread1.interrupt();

		System.out.println("thread1 is interrupt:"+thread1.isInterrupted());
//		System.out.println("thread2 is interrupt:"+thread2.isInterrupted());

		System.out.println("main thread start");

	}
}
