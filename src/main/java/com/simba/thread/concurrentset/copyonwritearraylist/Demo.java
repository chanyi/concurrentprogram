package com.simba.thread.concurrentset.copyonwritearraylist;

import java.util.concurrent.CopyOnWriteArrayList;

public class Demo {

	public static void main(String[] args) {
		//每次写的时候都建一个新的数组，把数据放入到的新数组中。
		//同步读的时候，读的是旧数组，写的是新数组。所以可以同步执行。
		//支持并发，但是内存消耗很大
		CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
	}
}
