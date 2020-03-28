package com.chaohappy.java8.stream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import com.chaohappy.java8.lambda.Person;

/*
 * 	终止操作
 */
public class TestStreamAPI3 {

	List<Person> persons = Arrays.asList(
			new Person("张一",1,"删除"),
			new Person("张二",2,"删除"),
			new Person("张三",3,"下线"),
			new Person("张四",4,"下线"),
			new Person("张五",5,"上线"),
			new Person("张六",6,"上线"),
			new Person("张六",8,"上线"),
			new Person("张6",8,"删除")
		);
	
	/*
	 * 	查找与匹配
	 * 	allMatch——检查是否匹配所有元素
	 * 	anyMatch——检查是否至少匹配一个元素
	 * 	noneMatch——检查是否没有匹配所有元素
	 * 	findFirst——返回第一个元素
	 * 	findAny——返回当前流中的任意元素
	 * 	count——返回流中元素的总个数
	 * 	max——返回流中最大值
	 * 	min——返回流中最小值
	 */
	@Test
	public void test1() {
		boolean allMatch = persons.stream().allMatch((e)->e.getStatus().equals("上线"));
		System.out.println("是否匹配所有的上线用户："+allMatch);
		boolean anyMatch = persons.stream().anyMatch((e)->e.getStatus().equals("上线"));
		System.out.println("至少匹配一个上线用户："+anyMatch);
		boolean noneMatch = persons.stream().noneMatch((e)->e.getStatus().equals("上线"));
		System.out.println("没有匹配到上线用户："+noneMatch);
		
		Person findFirst = persons.stream()
				.sorted((e1,e2)->e1.getAge().compareTo(e2.getAge()))
				.filter((x)->x.getStatus().equals("上线"))
				.findFirst().orElse(new Person());
		System.out.println("年龄最小的上线用户："+findFirst);
	
		Person findAny = persons.stream()
				.sorted((e1,e2)->e1.getAge().compareTo(e2.getAge()))
				.filter((x)->x.getStatus().equals("上线"))
				.findAny().orElse(new Person());
		System.out.println("随便找个年龄最小的上线用户："+findAny);
		
		long count = persons.stream()
				.filter((x)->x.getStatus().equals("上线"))
				.count();
		System.out.println("上线的用户数量："+count);
		
		Optional<Person> max = persons.stream()
				.max((x,y)->x.getAge().compareTo(y.getAge()));
		System.out.println("年龄最大的用户："+max.get());
		
		Optional<Integer> min = persons.stream()
				.map(Person::getAge)
				.min(Integer::compareTo);
		System.out.println("年龄最小的用户："+min.get());
		
	}
	
	/*
	 * 规约
	 * reduce(BinaryOperator)/reduce(T,BinaryOperator)——可以将流中元素反复结合起来，得到一个值。
	 */
	@Test
	public void test2() {
		List<Integer> list = Arrays.asList(1,2);
		Optional<Integer> reduce = list.stream()
			.reduce((x,y)->x+y);
		System.out.println(reduce.get());
		System.out.println("------------------------------");
		
		Optional<Integer> reduce2 = persons.stream()
				.map(Person::getAge)
				.reduce(Integer::sum);
		System.out.println("年龄总和："+reduce2.get());
	}
	
	/*
	 * 收集
	 * collect——将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素汇总的方法。
	 * Collector接口中方法的实现决定了如何对流执行收集操作（如收集到List、Set、Map）。Collectors实现类提供了很多静态方法，可以很方便地创建收集器实例。
	 */
	@Test
	public void test3() {
		List<Integer> collect = persons.stream()
				.map(Person::getAge)
				.collect(Collectors.toList());
		collect.forEach(System.out::println);
		System.out.println("------------------------------");
		
		HashSet<Integer> collect2 = persons.stream()
				.map(Person::getAge)
				.collect(Collectors.toCollection(HashSet::new));
				collect2.stream().forEach(System.out::println);
				
		System.out.println("------------------------------");
		//总数		
		Long collect3 = persons.stream()
				.collect(Collectors.counting());
		System.out.println("用户的总数："+collect3);
		System.out.println("------------------------------");
		
		//平均值
		Double collect4 = persons.stream()
				.collect(Collectors.averagingInt(Person::getAge));
		System.out.println("年龄平均值："+collect4);
		System.out.println("------------------------------");
		
		//总和
		Integer collect5 = persons.stream()
				.collect(Collectors.summingInt(Person::getAge));
		System.out.println("年龄总和："+collect5);
		System.out.println("------------------------------");

		//最大值
		Optional<Person> max = persons.stream().max((x,y)->x.getAge().compareTo(y.getAge()));
		System.out.println("年龄最大的用户："+max.get());
		
		//最小值
		System.out.println("------------------------------");
		Optional<Person> min = persons.stream().min((x,y)->x.getAge().compareTo(y.getAge()));
		System.out.println("年龄最小的用户："+min.get());
		System.out.println("------------------------------");
		
		//分组
		 Map<String, List<Person>> collect6 = persons.stream()
		 		.collect(Collectors.groupingBy(Person::getStatus));
		 System.out.println("根据登录状态分组："+collect6);
		 System.out.println("------------------------------");
		 
		//多级分组
		 Map<String, Map<String, List<Person>>> collect7 = persons.stream()
		 		.collect(Collectors.groupingBy(Person::getStatus, Collectors.groupingBy((e)->{
		 			if(((Person)e).getAge()<=3) {
		 				return "青年";
		 			}else if(((Person)e).getAge()>3) {
		 				return "中年";
		 			}
					return "无";
		 		})));
		 System.out.println("根据状态和年龄分组："+collect7);
		 
		 System.out.println("------------------------------");
		 //分区
		 Map<Boolean, List<Person>> collect8 = persons.stream().collect(Collectors.partitioningBy((e)->e.getAge()>5));
		 System.out.println("分区："+collect8);
		 
		 System.out.println("------------------------------");
		 //按int统计
		 IntSummaryStatistics collect9 = persons.stream().collect(Collectors.summarizingInt(Person::getAge));
		 System.out.println(collect9.getAverage());
		 System.out.println(collect9.getCount());
		 System.out.println(collect9.getSum());
		 System.out.println(collect9.getMax());
		 System.out.println(collect9.getMin());
		 System.out.println("------------------------------");
		 
		 //字符串连接
		 String collect10 = persons.stream().map(Person::getName).collect(Collectors.joining(","));
		 System.out.println("将所有姓名逗号拼接："+collect10);
		 System.out.println("------------------------------");
	}
}
