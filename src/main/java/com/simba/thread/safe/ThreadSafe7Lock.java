package com.simba.thread.safe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义一个锁，实现Lock接口
 * 要求：
 * 1、实现锁的基本功能
 * 2、可重入
 */
public class ThreadSafe7Lock implements Lock {
	private boolean islocked = false;

	Thread lockBy = null;

	int lockCount = 0;

	@Override
	public synchronized void lock() {
		//比较当前线程和锁的线程是否是同一个线程，如果一样，可以重入
		Thread currentThread = Thread.currentThread();
		while (islocked){
			if(currentThread != lockBy){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
		islocked = true;
		lockBy = currentThread;
		lockCount ++;
	}

	@Override
	public void unlock() {
		if(lockBy == Thread.currentThread()){
			lockCount --;
			if(lockCount == 0){
				islocked = false;
				notify();
			}

		}

	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}



	@Override
	public Condition newCondition() {
		return null;
	}
}
