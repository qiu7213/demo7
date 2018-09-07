package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.net.www.http.HttpClient;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {

		UUID U  =  UUID.randomUUID();
		System.out.println("uuid ： "+UUID.randomUUID().toString());


		System.out.println(Season.valueOf(2));
		System.out.println(Season.valueOf(3));
		System.out.println(Season.values());
		System.out.println(Season.SPRING);
		System.out.println(Season.valueOf("WINTER"));
		System.out.println(UUID.randomUUID().toString());



		System.out.println("2 : " + Season2.valueOf(2));
		System.out.println("2 : " + Season2.valueOf(3));
		System.out.println("2 : " + Season2.values());
		System.out.println("2 : " + Season2.SPRING);
		System.out.println("2 : " + Season2.valueOf("WINTER"));
		System.out.println("2 : " + UUID.randomUUID().toString());
	}

	enum Season {
		SPRING, SUMMER, AUTUMN, WINTER;
		public static Season valueOf(int index) {
			if (index < 0 || index >= values().length) {
				throw new IndexOutOfBoundsException("Invalid ordinal");
			}
			return values()[index];
		}
	}

	enum Season2 {
		SPRING(10,"春天"), SUMMER(20,"夏天"), AUTUMN(30,"秋天"), WINTER(40,"冬天");
		public static Season2 valueOf(int index) {
			if (index < 0 || index >= values().length) {
				throw new IndexOutOfBoundsException("Invalid ordinal");
			}
			return values()[index];
		}
		private int code;
		private String msg;
		Season2(int code,String msg){
			this.code = code;
			this.msg = msg;
		}
	}

}
