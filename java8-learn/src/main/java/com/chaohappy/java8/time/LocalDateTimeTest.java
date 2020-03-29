package com.chaohappy.java8.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import org.junit.Test;

public class LocalDateTimeTest {

	/*
	 * 1、LocalDate
	 * 2、LocalTime
	 * 3、LocalDateTime
	 * 4、Instant：时间戳（以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值）
	 * 5、Duration：计算两个时间之间的间隔
	 * 6、Period：计算两个日期之间的间隔
	 * 7、TemporalAdjuster：时间矫正器。有时我们可能需要获取例如：将日期调整到“下个周日”等操作。
	 * 8、TemporalAdjusters：该类通过静态方法提供了大量的常用TemporalAdjuster的实现。
	 * 9、DateTimeFormatter：格式化时间/日期
	 * 10、 时区的处理：ZonedDate、ZonedTime、ZonedDateTime
	 */
	@Test
	public void test1() {
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		
		LocalDateTime of = LocalDateTime.of(2020, 03, 29, 12, 12);
		System.out.println(of);
		
		LocalDateTime plusYears = of.plusYears(2);
		System.out.println("增加两年的时间："+plusYears);
		
		LocalDateTime minusMonths = of.minusMonths(2);
		System.out.println("减两个月的时间："+minusMonths);
		System.out.println(of.getYear());
		System.out.println(of.getMonth().getValue());
		System.out.println(of.getDayOfMonth());
		System.out.println(of.getMinute());
		
		System.out.println("---------时间戳----------------");
		Instant now2 = Instant.now();//获取UTC时区
		OffsetDateTime atOffset = now2.atOffset(ZoneOffset.ofHours(8));
		System.out.println("偏移8个小时的时区时间："+atOffset);
		long epochMilli = now2.toEpochMilli();
		System.out.println("当前时间的时间戳："+epochMilli);
		
		System.out.println("---------时间间隔----------------");
		Instant now3 = Instant.now();
		Instant now4 = Instant.now();
		Duration between = Duration.between(now3, now4);
		long millis = between.toMillis();
		System.out.println("时间间隔："+millis);
		
		LocalDateTime now5 = LocalDateTime.now();
		LocalDateTime now6 = LocalDateTime.now();
		long millis2 = Duration.between(now5, now6).toNanos();
		System.out.println("时间间隔："+millis2);
		
		System.out.println("---------日期间隔----------------");
		LocalDate now7 = LocalDate.of(1992, 9, 26);
		LocalDate now8 = LocalDate.now();
		Period between2 = Period.between(now7, now8);
		int years = between2.getYears();
		int months = between2.getMonths();
		int days = between2.getDays();
		System.out.println("日期间隔："+years+"年，"+months+"月"+days+"天");
		
		System.out.println("---------时间矫正----------------");
		LocalDateTime now9 = LocalDateTime.now();
		System.out.println(now9);
		LocalDateTime withDayOfMonth = now9.withDayOfMonth(11);
		System.out.println(withDayOfMonth);
		LocalDateTime with = now9.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println("下个周日日期："+with);
		
		//自定义下个工作日
		LocalDateTime with2 = now9.with((l)->{
			LocalDateTime ldt = (LocalDateTime)l;
			DayOfWeek dayOfWeek = ldt.getDayOfWeek();
			if(dayOfWeek.equals(DayOfWeek.FRIDAY)) {
				return ldt.plusDays(3);
			}else if(dayOfWeek.equals(DayOfWeek.SATURDAY)) {
				return ldt.plusDays(2);
			}else {
				return ldt.plusDays(1);
			}
		});
		System.out.println("下个工作日日期："+with2);
		
		System.out.println("---------时间格式化----------------");
		DateTimeFormatter dt = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime now10 = LocalDateTime.now();
		String format = dt.format(now10);
		System.out.println("格式化的时间："+format);
		//自定义时间格式
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
		String format2 = ofPattern.format(now10);
		System.out.println("yyyy年MM月dd日 HH:mm:ss："+format2);

		System.out.println("---------时区的处理----------------");
		Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
		availableZoneIds.forEach(System.out::println);
		
		LocalDateTime now11 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println("Asia/Hebron 时间："+now11);
		ZonedDateTime atZone = now11.atZone(ZoneId.of("Asia/Shanghai"));
		System.out.println("Asia/Hebron 时间："+atZone);
	}
}
