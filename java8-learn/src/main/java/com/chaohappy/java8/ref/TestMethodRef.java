package com.chaohappy.java8.ref;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import org.junit.Test;

/*
 * 	方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 * 	（可以理解为方法引用是Lambda表达式的另外一种表现形式）
 * 
 * 	主要有三种语法格式
 * 	
 * 1、对象：：实例方法名
 * 2、类：：静态方法名
 * 3、类：：实例方法名
 * 
 * 	使用要求： 
 * 		1、Lambda体中调用方法参数与返回值类型与函数式接口中抽象方法函数列表、返回值一致
 * 		2、若Lambda参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 * 
 * 	构造器引用
 * 
 */
public class TestMethodRef {
	
	
	/*
	 * 1、对象：：实例方法名
	 */
	@Test
	public void test1() {
		Consumer<String> con = (x) -> System.out.println(x);
		
		PrintStream ps = System.out;
		Consumer<String> con1 = ps::println;
		
		Consumer<String> con2 = System.out::printf;
		con2.accept("好吃不贵");
	}
	
	/*
	 * 2、类：：静态方法名
	 */
	@Test
	public void test2() {
		Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
		//
		Comparator<Integer> com1 = Integer::compare;
	}
	
	/*
	 * 3、类：：实例方法名
	 */
	@Test
	public void test3() {
		BiPredicate<String, String> bp = (x,y) -> x.equals(y);
		
		BiPredicate<String, String> bp1 = String::equals;
	}

}
