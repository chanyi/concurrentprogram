package com.simba.thread.Communication;

import java.util.concurrent.TimeUnit;

public class Thread1Volatile2 {

	private volatile int signal;

	public synchronized int getSignal() {
		System.out.println(Thread.currentThread().getName() +" do  get()");
		if(signal != 1){
			try {
				//当代码执行完wait后，synchronized锁已经释放，这样其他的线程也可以进入方法。
				//但是不能继续执行 都会在此等后唤醒
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() +" do  get() success ");
		return signal;
	}

	public synchronized void setSignal(int signal) {
		this.signal = signal;
		//notify方法会随机叫醒一个处于wait状态的线程，让线程继续执行
		notify();
		//notifyAll方法会叫醒所有处于wait状态的线程，但是争夺到时间片的线程只有一个
		//notifyAll();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//等待此5s之后 wait状态的才被唤醒
		System.out.println("notify 5s 执行完毕 ");

	}

	public static void main(String[] args) {

		Thread1Volatile2 thread1Volatile2 = new Thread1Volatile2();
		//执行wait方法
		TargetRunnable t1 = new TargetRunnable(thread1Volatile2);
		//执行notify方法
		TargetRunnable2 t2 = new TargetRunnable2(thread1Volatile2);
		new Thread(t1).start();
		new Thread(t1).start();
		new Thread(t1).start();
		new Thread(t1).start();

		//新建一个线程执行任务t2（也就是执行一次notify方法）

		//先休息一秒
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//执行nofity的任务
		new Thread(t2).start();

		//当执行过一次nofity方法之后，四个wait的线程将随机的被唤醒一个，然后继续执行wait后的代码



	}

}
