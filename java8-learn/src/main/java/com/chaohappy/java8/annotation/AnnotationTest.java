package com.chaohappy.java8.annotation;

import java.lang.reflect.Method;

import org.junit.Test;

public class AnnotationTest {

	@MyAnnotation("hello")
	@MyAnnotation("hello1")
	public void show() {
	}
	
	@Test
	public void test1() throws NoSuchMethodException, SecurityException {
		Class<AnnotationTest> clazz = AnnotationTest.class;
		Method method = clazz.getMethod("show");
		MyAnnotation[] annotationsByType = method.getAnnotationsByType(MyAnnotation.class);
		for (MyAnnotation myAnnotation : annotationsByType) {
			System.out.println("注解值："+myAnnotation.value());
		}
	}
}
