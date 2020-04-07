package com.simba.thread.Communication.exchanger;
import java.util.concurrent.Exchanger;

/**
 *两个线程的数据交换，A线程把数据给B，B线程把数据给A
 * 常使用在遗传算法和管道中。
 */
public class Test {

	public void a (Exchanger<String> stringExchanger){
		System.out.println("a方法开始执行。。。");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String result = "a";

		try {
			String  value = stringExchanger.exchange(result);
			System.out.println("a方法拿到的数据是："+value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("a方法执行完毕。。。");

	}

	public void b (Exchanger<String> stringExchanger){
		System.out.println("b方法开始执行。。。");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String result = "b";

		try {
			String value = stringExchanger.exchange(result);
			System.out.println("b方法拿到的数据是：" + value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("b方法执行完毕。。。");

	}

	public static void main(String[] args) {
		Test test = new Test();
		Exchanger<String> exchanger = new Exchanger<>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				test.a(exchanger);
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				test.b(exchanger);
			}
		}).start();
	}
}
