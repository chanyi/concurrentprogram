package com.simba.thread.Communication.ProducerConsumer;

public class Test {

	public static void main(String[] args) {
		Tmall tmall = new Tmall();
		PushTarget pushTarget = new PushTarget(tmall);
		TakeTarget takeTarget = new TakeTarget(tmall);

		new Thread(pushTarget){}.start();
		new Thread(pushTarget){}.start();
		new Thread(pushTarget){}.start();
		new Thread(pushTarget){}.start();

		new Thread(takeTarget){}.start();


	}
}
