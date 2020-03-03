package com.simba.thread.safe;

public class LazySingleModel {

	private LazySingleModel(){}

	//volatile可以使虚拟机不执行指令优化，解决指令重排序的问题，保证线程的安全性
	private static volatile LazySingleModel lazySingleModel;

	//可以使用synchronized关键字来解决多线程的问题
	//但是会影响到程序的性能，下一个线程来之后会一直自旋，自旋是比较消耗性能的。
	//优化思路是，缩小锁的是用范围，只有创建的时候会出现线程问题，那么就只需要在创建的时候加上同步synchronized代码块
	public static LazySingleModel instance() throws InterruptedException {
		if (lazySingleModel == null){
//			Thread.sleep(100);
			synchronized (LazySingleModel.class){
				if(lazySingleModel == null){
					lazySingleModel = new LazySingleModel();
					//整个代码在执行中可能会出现代码重排序现象，所以还是会出现线程安全问题
					//在new的过程中执行的步骤有：
					//1、申请一块内存空间
					//2、在空间中实例化对象
					//3、对象指向内存空间
					//所以指令重排序的情况，可能会是执行了132。则判断就会出现错误
					//解决这一问题的方案是使用关键字volatile
				}
			}

		}
		return lazySingleModel;
	}


}
