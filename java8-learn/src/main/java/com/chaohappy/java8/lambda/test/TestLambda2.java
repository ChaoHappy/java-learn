package com.chaohappy.java8.lambda.test;

import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * 
 * - Java8中引入了一个新的操作符“->”该操作符称为箭头操作符或Lambda操作符；
 * - 箭头操作符将Lambda表达式拆分为两部分：
 *	 左侧：Lambda表达式的参数列表
 *	右侧：Lambda表达式中所需要执行的功能，即Lambda体
 *
 *	口诀：
 * 	左右遇一括号省
 * 	左侧推断类型省
 * 	
 */
public class TestLambda2 {
	/*
	 * 	语法格式一：无参数，无返回值 
	 *  () -> System.out.println("Hello Lambda!");
	 */
	public void test1() {
		Runnable r =new Runnable() {
			
			public void run() {
				System.out.println("Hello World!");
			}
		};
		
		Runnable r1 = () ->System.out.println("Hello World!");
	}
	
	
	/*
	 * 	语法格式二：有一个参数，无返回值
	 *  (x) -> System.out.println(x);
	 */
	@Test
	public void test2() {
		Consumer<String> con = (x) -> System.out.println(x);
		con.accept("学习Lambda");
	}
	
	/*
	 * 	语法格式三：若只有一个参数，小括号可以省略不写
	 *  x -> System.out.println(x);
	 */
	public void test3() {
		Consumer<String> con = x -> System.out.println(x);
		con.accept("学习Lambda");
	}
	
	
	/*
	 * 	语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
	 *  Comparator<Integer> com = (x,y) -> {
	 *		System.out.println("函数式接口，比较x："+x+"  y："+y);
	 *		return Integer.compare(x, y);
	 *	};
	 */
	public void test4() {
		Comparator<Integer> com = (x,y) -> {
			System.out.println("函数式接口，比较x："+x+"  y："+y);
			return Integer.compare(x, y);
		};
		com.compare(22, 33);
	}
	
	/*
	 * 	语法格式五：若Lambda体中只有一条语句，return和大括号都可以省略不写
	 *  Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
	 */
	public void test5() {
		Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
		com.compare(22, 33);
	}
	
	/*
	 * 	语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出数据类型，即“类型推断”
	 *  Comparator<Integer> com = (Integer x,Integer y) -> Integer.compare(x, y);
	 */
	public void test6() {
		Comparator<Integer> com = (Integer x,Integer y) -> Integer.compare(x, y);
		com.compare(22, 33);
	}
	
	/*
	 *	需求： 对一个数进行运算 
	 *	
	 */
	public void test7() {
		operation(100, (x) -> x * x);
	}
	
	public Integer operation(Integer num,MyFun mf) {
		return mf.getValue(num);
	}
}
