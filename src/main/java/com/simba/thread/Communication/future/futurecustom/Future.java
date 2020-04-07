package com.simba.thread.Communication.future.futurecustom;

public class Future {

	private Product product;

	private boolean done = false;

	public synchronized Product getProduct() {
		while(!done){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return product;
	}

	public synchronized void setProduct(Product product) {
		if(done){
			return;
		}
		this.product = product;
		this.done = true;
		notifyAll();
	}
}
