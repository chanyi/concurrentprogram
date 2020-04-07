package com.simba.thread.concurrentset.blockqueue;

public class TakeTarget implements Runnable {

	private Tmall tmall;
	public TakeTarget (Tmall tmall){
		this.tmall = tmall;
	}
	@Override
	public void run() {
		tmall.take();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
