package com.simba.thread.Communication;

public class Thread1Volatile {

	private volatile int signal;

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public static void main(String[] args) {
		//当线程1将signle改为1之后，线程2发现signle发现signle==1 才继续执行
		Thread1Volatile thread1Volatile = new Thread1Volatile();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread()+"do...");
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				thread1Volatile.setSignal(1);
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (thread1Volatile.getSignal() !=1){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread()+"do...");
				if(thread1Volatile.getSignal() == 1){
					System.out.println("sign == 1 ");
				}

			}
		}).start();


	}

}
