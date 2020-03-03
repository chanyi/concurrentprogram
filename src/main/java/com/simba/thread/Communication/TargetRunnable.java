package com.simba.thread.Communication;


/**
 * 任务1
 */
public class TargetRunnable implements Runnable {

	private Thread1Volatile2 thread1Volatile2;

	public TargetRunnable (Thread1Volatile2 thread1Volatile2){
		this.thread1Volatile2 = thread1Volatile2;

	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" run ...");
		thread1Volatile2.getSignal();
	}
}
