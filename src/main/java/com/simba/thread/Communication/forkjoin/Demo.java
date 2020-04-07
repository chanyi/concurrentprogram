package com.simba.thread.Communication.forkjoin;

import com.simba.thread.Communication.future.futurecustom.Future;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 将一个任务分成多部分，分别交给cpu去处理。处理完之后再合成起来
 */
public class Demo extends RecursiveTask<Long> {

	private int start;
	private int end;

	public Demo(){
		super();
	}

	public Demo(int start,int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
//		System.out.println("线程："+Thread.currentThread().getName());
		Long sum =0L;
		if(end-start<=2){
			for(int i=start;i<=end;i++){
				sum+=i;
			}
		}else{
			Demo demo1 = new Demo(start,(end+start)/2);
			Demo demo2 = new Demo((end+start)/2+1,end);
			//执行子任务
			demo1.fork();
			demo2.fork();
			//合并子任务
			Long sum1 = demo1.join();
			Long sum2 = demo2.join();

			sum=sum1+sum2;

		}
		return sum;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		//将任务分成多个块，开多个线程执行，未必就是比单线程执行的块。适用于多核机器。
		long startTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		System.out.println("创建线程forkjoin池成功，开始提交任务");
		ForkJoinTask<Long> future = forkJoinPool.submit(new Demo(1,1000000000));
		System.out.println("已提交任务，获取数据中。。。。");
		long result = future.get();
		long endTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
		System.out.println("计算结果："+result+".总耗时："+(endTime-startTime));
		System.out.println("=========================");
		long startTime1 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
		long result1 = Demo.sum(1,1000000000);
		long endTime1 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
		System.out.println("结果是:"+result1+"总耗时:"+(endTime1-startTime1));

	}

	public static long sum(int start,int end){
		long sum = 0;
		for (int i = start;i<=end;i++){
			sum += i ;
		}
		return sum;
	}
}
