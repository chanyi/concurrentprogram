package com.simba.thread.safe;

import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

	private int value;

	//解决线程安全性问题的三个方案
	//1、使用原子类操作
	//2、使用synchronized关键字
	//3、使用lock

	//（1）、解决线程问题也可以使用java提供的原子类操作

	private AtomicInteger atomicInteger = new AtomicInteger(0);

	private int[] arr = {1,2,3,4};

	private AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(arr);

	//只对user的实例的get和set进行原子性操作，并不对user中的对象的操作进行原子操作
	private AtomicReference<User> userAtomicReference = new AtomicReference<>();
	//更新某个类的字段
	//使用这个的要求是：
	//1、属性必须public
	//2、属性必须volatile
	//3、不能加static不能加final
	//原理就是使用CAS，CompareAndSet
	private AtomicReferenceFieldUpdater atomicReferenceFieldUpdater =
			AtomicReferenceFieldUpdater.newUpdater(User.class,String.class,"name");

	public int getAtomicInterge(){

		User user = new User();
		//如果类的name属性是zhagnsan，将其值改为lisi，此方法返回true
		atomicReferenceFieldUpdater.compareAndSet(user,"zhangsan","lisi");

		//现获取在执行加操作
		return atomicInteger.getAndIncrement();

	}

	//（2）、使用synchronized关键字
	//会出现线程安全性问题
	//	public int getNext(){
	//不会出现线程安全性问题 但是会影响效率
	public synchronized int getNext(){

		return value++;
	}

	//synchronized修饰静态方法
	public static synchronized int get(){
		return 0;
	}

	//synchronized修饰代码块
	public int getInteger(){
		synchronized (Integer.valueOf(value)){
			System.out.println("value :"+value);
		}
		return value;
	}


	//（3）、使用lock

	Lock lock = new ReentrantLock();


	public int getLockValue(){
		//加锁然后释放锁
		lock.lock();
		value++;
		lock.unlock();
		return value;
	}

}
