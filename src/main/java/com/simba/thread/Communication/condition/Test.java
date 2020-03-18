package com.simba.thread.Communication.condition;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

	private int signal = 0;

	Lock lock = new ReentrantLock();

	Condition conditionA = lock.newCondition();
	Condition conditionB = lock.newCondition();
	Condition conditionC = lock.newCondition();

	public void a(){
		lock.lock();
		while (signal != 0){
			try {
				conditionA.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("a");
		signal++;
		conditionB.signal();
		lock.unlock();
	}

	public void b(){
		lock.lock();
		while (signal != 1){
			try {
				conditionB.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("b");
		signal++;
		conditionC.signal();
		lock.unlock();
	}

	public void c(){
		lock.lock();
		while (signal != 2){
			try {
				conditionC.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("c");
		signal = 0;
		conditionA.signal();
		lock.unlock();
	}



	public static void main(String[] args) {

		Test test = new Test();

		A a  = new A(test);
		B b  = new B(test);
		C c  = new C(test);

		new Thread(a).start();
		new Thread(b).start();
		new Thread(c).start();

	}

}



class A implements Runnable{

	private Test test;

	public A(Test test){
		this.test = test;
	}

	@Override
	public void run() {
		while (true){
			test.a();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

class B implements Runnable{
	private Test test;
	public B(Test test){
		this.test = test;
	}
	@Override
	public void run() {
		while (true){
			test.b();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class C implements Runnable{

	private Test test;

	public C(Test test){
		this.test = test;
	}
	@Override
	public void run() {

		while(true){
			test.c();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}


