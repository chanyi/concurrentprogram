package com.simba.thread.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 使用spring的方式创建线程
 */

public class CreateThread7 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
		CreateThread7Service createThread7Service = annotationConfigApplicationContext.getBean(CreateThread7Service.class);
		createThread7Service.doThread1();
		createThread7Service.doThread2();
	}
}
