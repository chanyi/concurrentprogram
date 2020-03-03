package com.simba.thread.create;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 创建定时器任务
 * 定时器中创建一个定时任务TimerTask，TimerTask实现了runnable方法
 */
public class CreateThread5 {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("实现run方法");
				System.out.println("执行定时任务");
			}
		},0,1000);
	}


}
