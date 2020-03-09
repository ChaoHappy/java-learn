package com.chaohappy.java8.lambda;

import org.junit.Test;

public class TestLambda2 {

	/**
	 * 1、声明一个带两个泛型的函数式接口，泛型类型为<T,R>T为参数，R为返回值
	 * 2、接口中声明对应抽象方法
	 * 3、在下面方法中，使用接口作为参数，计算两个long型参数的和
	 * 4、再计算两个long型参数的乘积
	 */
	@Test
	public void test1() {
		op(100L, 200L, (x,y) -> x+y);
		op(100L, 200L, (x,y) -> x*y);
	}
	
	private void op(Long l1,Long l2, MyFun2<Long, Long> mf3) {
		Long value = mf3.getValue(l1, l2);
		System.out.println("计算后的值为："+value);
	}
}
