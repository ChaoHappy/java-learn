package com.chaohappy.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/*
 * Java8 内置的四大核心函数式接口
 * 
 * 1、Consumer<T> :消费型接口
 * 		void accept(T t);
 * 
 * 2、Supplier<T> : 供给型接口
 * 		T get();
 * 
 * 3、Function<T,R> : 函数型接口
 * 		R apply(T t);
 * 
 * 4、Predicate<T> : 断言型接口
 * 		boolean test(T t);
 */
public class TestLambda3 {
	
	/*
	 * 1、Consumer<T> :消费型接口
	 */
	@Test
	public void test1() {
		happy(1000.0, (x) -> System.out.println("消费："+x));
	}
	
	public void happy(Double money , Consumer<Double> con) {
		con.accept(money);
	}
	
	/*
	 * 2、Supplier<T> : 供给型接口
	 * T get();
	 */
	@Test
	public void test2() {
		List<Integer> numList = getNumList(3, () -> (int)(Math.random()*100));
		for (Integer integer : numList) {
			System.out.println(integer);
		}
	}
	
	/*
	 * 	 需求 : 产生指定个数的整数，并放入集合
	 */
	public List<Integer> getNumList(int num , Supplier<Integer> sup) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			Integer integer = sup.get();
			list.add(integer);
		}
		return list;
	}
	
	
	/*
	 * 3、Function<T,R> : 函数型接口
	 * 		R apply(T t);
	 */
	@Test
	public void test3() {
		String strHandler = strHandler("我爱天津", (x) -> {
			pr();
			return x+"!";
		});
		System.out.println(strHandler);
	}
	
	private void pr() {
		System.out.println("============");
	}
	
	/*
	 * 	需求：用于处理字符串
	 */
	public String strHandler(String str ,Function<String, String> fun) {
		return fun.apply(str);
	}
	
	
	/*
	 * 4、Predicate<T> : 断言型接口
	 * 		boolean test(T t);
	 */
	@Test
	public void test4() {
		List<String> list = new ArrayList<String>();
		list.add("222");
		list.add("3333");
		list.add("44444");
		list.add("555555");
		List<String> filterStr = filterStr(list, (x)-> x.length() >4);
		for (String string : filterStr) {
			System.out.println(string);
		}
	}
	
	/*
	 * 	需求：满足条件的字符串放入集合中
	 */
	public List<String> filterStr(List<String> list ,Predicate<String> pre){
		List<String> strList = new ArrayList<String>();
		for (String string : list) {
			if(pre.test(string)) {
				strList.add(string);
			}
		}
		return strList;
	} 

}
