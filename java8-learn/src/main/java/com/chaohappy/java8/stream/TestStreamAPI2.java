package com.chaohappy.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.chaohappy.java8.lambda.Person;

/*
 * 一、Stream 的三个操作步骤
 * 
 * 1. 创建Stream
 * 
 * 2. 中间操作
 * 	筛选与切片
 *  filter(Predicate p)——接收Lambda,从流中排除某些元素。
 *  limit(long maxSize)——截断流，使其元素不超过给定数量。
 *  skip(long n)——跳过元素，返回一个扔掉了n个元素的流。若流中元素不足n个，返回一个空流。与limit(n)互补。
 *  distinct()——筛选，通过流所生成元素的hashCode()和equals()去除重复元素。
 * 
 * 3. 终止操作（终端操作）
 */
public class TestStreamAPI2 {
	
	List<Person> persons = Arrays.asList(
			new Person("张一",1),
			new Person("张二",2),
			new Person("张三",3),
			new Person("张四",4),
			new Person("张五",5),
			new Person("张六",6),
			new Person("张六",7),
			new Person("张六",8)
		);
	
	
	/*
	 *	内部迭代：迭代操作由Stream API完成	
	 *	filter(Predicate p)——接收Lambda,从流中排除某些元素。
	 *	limit(long maxSize)——截断流，使其元素不超过给定数量。
	 */
	@Test
	public void test1() {
		Stream<Person> stream = persons.stream()
									   .filter((e)->{
										   System.out.println("短路");
										   return e.getAge()<4;
									   })
									   .limit(2);
		stream.forEach(System.out::println);
	}
	
	/*
	 * skip(long n)——跳过元素，返回一个扔掉了n个元素的流。若流中元素不足n个，返回一个空流。与limit(n)互补。
	 */
	@Test
	public void test2() {
		Stream<Person> stream = persons.stream()
				   .filter((e)->{
					   System.out.println("短路");
					   return e.getAge()<4;
				   })
				   .skip(2);
		stream.forEach(System.out::println);
	}
	
	/*
	 * distinct()——筛选，通过流所生成元素的hashCode()和equals()去除重复元素。
	 */
	@Test
	public void test3() {
		Stream<Person> stream = persons.stream()
				   .filter((e)->{
					   return e.getAge()>3;
				   })
				   .distinct();
		stream.forEach(System.out::println);
	}

	//外部迭代
	@Test
	public void test4() {
		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	/*
	 * - map——接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
	 * - flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
	 */
	@Test
	public void test5() {
		List<String> strs = Arrays.asList("张一","张二",	"张三","张四","张五","张六");
		Stream<String> s1 = strs.stream().map((str)->str+"加油！");
		s1.forEach(System.out::println);
		System.out.println("-------------------------");
		Stream<String> s2 = persons.stream().map(Person::getName);
		s2.forEach(System.out::println);
		
		System.out.println("-------------------------");
		Stream<Stream<Character>> s3 = strs.stream().map(TestStreamAPI2::filterCharacter);
		s3.forEach((sm)->{
			sm.forEach(System.out::println);
		});
		
		System.out.println("------------flatMap-------------");
		Stream<Character> s4 = strs.stream().flatMap(TestStreamAPI2::filterCharacter);
		s4.forEach(System.out::println);
		
	}
	
	public static Stream<Character> filterCharacter(String str){
		List<Character> list = new ArrayList<Character>();
		for (Character ch : str.toCharArray()) {
			list.add(ch);
		}
		return list.stream();
	}
	
	/**
	 * 	中间操作——排序
	 * 	sorted——自然排序（Comparable）
	 * 	sorted(Comparator)——定制排序（Comparator）
	 */
	@Test
	public void test7() {
		List<String> list = Arrays.asList("cc","bb","dd","aa");
		
		list.stream().sorted().forEach(System.out::println);
		
		System.out.println("-----------");
		
		persons.stream().sorted((x,y)->{
							return -x.getAge().compareTo(y.getAge());
						})
						.forEach(System.out::println);;
	}
}
