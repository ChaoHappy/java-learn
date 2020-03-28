package com.chaohappy.java8.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.OptionalLong;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

import org.junit.Test;

public class ForkJoinTest {

	/*
	 * 传统的ForkJoin框架
	 */
	@Test
	public void test1() {
		Instant start = Instant.now();
		
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinCalculate task = new ForkJoinCalculate(0, 10000000000L);
		Long invoke = pool.invoke(task);
		System.out.println("合计值："+invoke);
		
		Instant end = Instant.now();
		//4194毫秒
		System.out.println(Duration.between(start, end).toMillis());
	}
	
	/*
	 * 普通for循环
	 */
	@Test
	public void test2() {
		Instant start = Instant.now();
		long sum = 0;
		for (int i = 0; i < 10000000000L; i++) {
			sum+=1;
		}
		Instant end = Instant.now();
		System.out.println(sum);
		
		System.out.println(Duration.between(start, end).toMillis());
	}
	
	/*
	 * Java8并行流
	 */
	@Test
	public void test3() {
		Instant start = Instant.now();
		OptionalLong reduce = LongStream.rangeClosed(0, 10000000000L)
					.parallel()
					.reduce(Long::sum);
		//7514毫秒
		System.out.println(reduce.getAsLong());
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end).toMillis());
	}
}
