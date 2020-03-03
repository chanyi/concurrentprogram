package com.simba.thread.Communication;


/**
 * 任务2
 */
public class TargetRunnable2 implements Runnable {

	private Thread1Volatile2 thread1Volatile2;

	public TargetRunnable2(Thread1Volatile2 thread1Volatile2){
		this.thread1Volatile2 = thread1Volatile2;

	}

	@Override
	public void run() {
		System.out.println("执行任务2的run方法。。。");
		thread1Volatile2.setSignal(1);
	}
}
