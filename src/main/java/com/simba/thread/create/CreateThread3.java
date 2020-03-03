package com.simba.thread.create;

import javax.sound.midi.Soundbank;

/**
 * 通过构造匿名内部类创建一个线程
 */
public class CreateThread3 {

	public static void main(String[] args) {
		//实现匿名内部类
//		new Thread(){
//			public void run(){
//				while (true){
//					System.out.println("thread go "+ getName() +"...");
//				}
//			}
//		}.start();

		//实现runnable方法
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true){
//					System.out.println("thread go ..");
//				}
//			}
//		}).start();

		//如果子类自己自己实现了run方法，那么执行子类的run
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parent");
			}
		}){
			public void run(){
				System.out.println("sub");
			}
		}.start();
	}

}
