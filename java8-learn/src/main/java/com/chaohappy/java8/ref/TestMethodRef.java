package com.chaohappy.java8.ref;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import com.chaohappy.java8.lambda.Person;

/*
 * 	一、方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 * 	（可以理解为方法引用是Lambda表达式的另外一种表现形式）
 * 
 * 	主要有三种语法格式
 * 	
 * 1、对象：：实例方法名
 * 2、类：：静态方法名
 * 3、类：：实例方法名
 * 
 * 	使用要求： 
 * 		1、Lambda体中调用方法参数与返回值类型与函数式接口中抽象方法函数列表、返回值一致（以上三种方式都需要满足这个规则）
 * 		2、若Lambda参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method（只针对第三个方式）
 * 
 * 	二、构造器引用
 * 		格式：
 * ClassName::new
 * 
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致。
 * 
 * 三、数组引用
 * 
 * 格式：Type::new;
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
		
		Person p = new Person();
		p.setName("中国加油！");
		Supplier<String> sup = p::getName;
		System.out.println("==="+sup.get());
	}
	
	/*
	 * 2、类：：静态方法名
	 */
	@Test
	public void test2() {
		//Lambda方式
		Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
		//方法引用方式
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
	
	/*
	 * 构造器引用
	 */
	@Test
	public void test4() {
		// Lambda方式
		Supplier<Person> sup1 = ()->new Person();
		
		//构造器引用
		Supplier<Person> sup2 = Person::new;
		Person person = sup2.get();
		System.out.println(person);
		
		
		Function<String, Person> fun1 = (x)-> new Person(x);
		
		Function<String, Person> fun2 = Person::new;
		Person person2 = fun2.apply("武汉加油！！");
		System.out.println(person2.getName());
	}
	
	/*
	 * 数组引用
	 */
	@Test
	public void test5() {
		// Lambda方式
		Function<Integer, String[]> fun1 = (x)-> new String[x];
		String[] arr1 = fun1.apply(5);
		System.out.println(arr1.length);
		//数组引用
		Function<Integer, String[]> fun2 = String[]::new;
		String[] arr2 = fun2.apply(88);
		System.out.println(arr2.length);
	}

}
