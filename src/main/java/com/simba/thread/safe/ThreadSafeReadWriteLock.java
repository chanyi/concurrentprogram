package com.simba.thread.safe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁（使用ReentrantReadWriteLock实现）
 */
public class ThreadSafeReadWriteLock {

	private Map<String,Object> map = new HashMap<>();

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	//初始化读锁
	private Lock readLock = readWriteLock.readLock();
	//初始化写锁
	private Lock writeLock = readWriteLock.writeLock();

	public Object get(String key){
		//使用读锁
		readLock.lock();
		System.out.println(Thread.currentThread()+"-do read lock --");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return map.get(key);
		}finally {
			System.out.println(Thread.currentThread()+"-do read unlock --");
			readLock.unlock();
		}
	}



	public void put(String key,Object value){
		//使用写锁
		writeLock.lock();
		System.out.println(Thread.currentThread()+"-do write lock --");
		try{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key,value);
		}finally {
			writeLock.unlock();
			System.out.println(Thread.currentThread()+"-do write unlock --");
		}

	}

}
