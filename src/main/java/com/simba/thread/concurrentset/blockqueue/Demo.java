package com.simba.thread.concurrentset.blockqueue;

public class Demo {

	//阻塞队列并不是所有的方法都是阻塞的
	//其中put() take() 是阻塞的
	//add() remove();不是阻塞 抛出异常
	//offer() poll();有返回值的（成功是true，失败false）

	public static void main(String[] args) {
		Tmall tmall = new Tmall();
		PushTarget pushTarget = new PushTarget(tmall);
		TakeTarget takeTarget = new TakeTarget(tmall);

		new Thread(pushTarget).start();
		new Thread(pushTarget).start();
		new Thread(pushTarget).start();
		new Thread(pushTarget).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					tmall.size();
				}

			}
		}).start();


		new Thread(takeTarget).start();
		new Thread(takeTarget).start();
		new Thread(takeTarget).start();
		new Thread(takeTarget).start();
	}
}
