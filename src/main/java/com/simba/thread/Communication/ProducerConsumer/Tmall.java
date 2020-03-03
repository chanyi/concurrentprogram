package com.simba.thread.Communication.ProducerConsumer;

public class Tmall {

	private int count;

	public void push(){
		count++;
	}

	public void take(){
		count--;
	}
}
