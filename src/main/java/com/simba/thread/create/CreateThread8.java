package com.simba.thread.create;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

/**
 * 通过lambda表达式创建线程
 */

public class CreateThread8 {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(10,20,30,40);
		while(true){
			System.out.println(add(list));
		}

	}

	public static Integer add(List<Integer> list){

		//使用parallelStream进行并行执行
		list.parallelStream().forEach(System.out::println);
		//按照顺序打印
		list.parallelStream().forEachOrdered(System.out::println);
		return list.parallelStream().mapToInt(a->a).sum();

	}
}
