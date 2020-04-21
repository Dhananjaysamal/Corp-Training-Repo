package com.example.demo.entity;

import java.util.Random;
import java.util.StringJoiner;

public class Test {

	public static void main(String[] args) {
		
//	      Random value = new Random();
//	      StringJoiner sj = new StringJoiner("");
//	      int n = 0;
//		    for(int i =0; i < 4;i++)
//		    {
//		    	 String start = "";
//		    	
//		            for(int j=0;j<4;) {
//		            	j++;
//		            	n = value.nextInt(10);
//		            	 start += Integer.toString(n);
//		            	     }
//		            sj.add(start);
//		            System.out.println(start);   
//		    }
//		    System.out.println(sj);
		
		
		Random value = new Random();
		String newCvv = Integer.toString(0);
		StringJoiner sj = new StringJoiner("");
		int n = 0;
		newCvv = "";

			for(int j=0;j<3;) {
				j++;
				n = value.nextInt(10);
				newCvv += Integer.toString(n);
			}
			sj.add(newCvv);
			System.out.println(sj);
	}

}
