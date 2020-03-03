package com.simba.thread.safe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * AQS(AbstractQueuedSynchronizer)类
 * 实现同步器的基础类
 */
public class ThreadSafeAQS implements Lock {

	private boolean islocked = false;

	private Thread lockBy = null;

	private int lockCount = 0;

	private Helper helper;

	//实现tryAcquire 和 tryRelease 方法 可以实现一个独占锁
	private class Helper extends AbstractQueuedSynchronizer{

		/**
		 * 获取锁
		 * @param arg
		 * @return
		 */
		@Override
		protected boolean tryAcquire(int arg) {
			//第一个线程进来可以拿到锁，返回true，设置state值，第二个线程进来state不为0，直接返回false
			//可重入，如果当前的线程是锁的线程直接进入
			int state = getState();
			Thread thread = Thread.currentThread();
			if(state ==0 ){
				if(compareAndSetState(0,arg)){
					setExclusiveOwnerThread(Thread.currentThread());
					return true;
				}
			}else if(getExclusiveOwnerThread() == thread){
				setState(state+1);
				return true;
			}
			return false;
		}

		/**
		 * 释放锁
		 * @param arg
		 * @return
		 */
		@Override
		protected boolean tryRelease(int arg) {
			if(Thread.currentThread() != getExclusiveOwnerThread()){
				throw new RuntimeException("此线程不是当前锁的线程，无法释放");
			}
			int state = getState()-arg;
			if(state == 0){
				//当前锁的线程设置为0
				setExclusiveOwnerThread(null);

			}
			setState(state);
			return super.tryRelease(arg);
		}
	}

	@Override
	public synchronized void lock() {
		//获取一个锁
		helper.tryAcquire(1);
	}

	@Override
	public void unlock() {
		//释放锁
		helper.tryRelease(1);
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
