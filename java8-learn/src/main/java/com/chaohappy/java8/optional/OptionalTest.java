package com.chaohappy.java8.optional;

import java.util.Optional;

import org.junit.Test;

import com.chaohappy.java8.lambda.Person;

public class OptionalTest {

	/*
	 * Optional容器常用方法：
	 * Optional.of(T t)——创建一个Optional实例
	 * Optional.empty()——创建一个空的Optional实例
	 * Optional.ofNullAble(T t)——若t不为null,创建Optional实例，否则创建空实例。
	 * isPresent()——判断是否包含值
	 * orElse(T t)——如果调用对象包含值，返回该值，否则返回t
	 * orElseGet(Supplier s)——如果调用对象包含值，返回该值，否则返回s获取的值
	 * map(Function f)——如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
	 * flatMap(Function mapper)——与map类似，要求返回值必须是Optional
	 */
	@Test
	public void test1() {
		Optional<Person> op = Optional.of(new Person());
		Person person = op.get();
		System.out.println("of："+person);
		
		Optional<Object> empty = Optional.empty();
		if(empty.isPresent()) {
			System.out.println("empty："+empty.get());
		}
		Object orElse = empty.orElse("没有值");
		System.out.println("orElse："+orElse);
		
		Object orElseGet = empty.orElseGet(()->new Person());
		System.out.println("orElseGet："+orElseGet);
		
		
		Optional<Person> ofNullable = Optional.ofNullable(new Person());
		System.out.println("ofNullable："+ofNullable.get());
		
		Optional<String> map = Optional.ofNullable(new Person("张三")).map((x)->x.getName());
		System.out.println("map："+map.get());
		
		Optional<String> flatMap = Optional.ofNullable(new Person("张三")).flatMap((x)->Optional.of(x.getName()));
		System.out.println("flatMap："+flatMap.get());
	}
}
