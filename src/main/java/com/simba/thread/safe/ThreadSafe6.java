package com.simba.thread.safe;

public class ThreadSafe6 {

	//通过volatile关键字 ，让不同线程对该变量可见
	//volatile关键字的实质上是lock指令在起作用
	//在多处理器器的系统上，将当前处理器缓存行上的内容写到系统内存中。写回到内存的这个操作会使在其他内存中缓存了该地址的数据失效
	//会导致性能降低的两个原因
	//1、禁止了指令重排序，性能变慢
	//2、cpu中的缓存失效了，一切按照内存中的数据为准。cpu缓存速度要远快于内存，所以性能变慢了
	private  int a = 1;

	private static volatile boolean  flag = false;

	public  int getA() {
		return a;
	}

	public  void setA(int a) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.a = a;
	}

	public static void main(String[] args) {
		ThreadSafe6 threadSafe6 = new ThreadSafe6();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("set do ...");
				threadSafe6.setA(10);
				System.out.println("set a");
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("get do ...");
				System.out.println("get a :"+threadSafe6.getA());
			}
		}).start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("last  -- a:"+threadSafe6.getA());



		//一个线程对flag值进行修改，另一个线程检测到flag值变化之后，进行对应的操作
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 9 ; i++) {
					System.out.println("thread go times "+i);
				}
				flag = true;
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!flag){
					//自旋
				}
				System.out.println("other thread go...");
			}
		}).start();

	}


}
