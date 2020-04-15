package com.example.demo.exception;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		 String start = "";
	        Random value = new Random();

	    //Generate two values to append to 'BE'
	    int r1 = value.nextInt(10);
	    int r2 = value.nextInt(10);
//	    start += Integer.toString(r1) + Integer.toString(r2);

	    int n = 0;
	    for(int i =0; i < 12;i++)
	    {
	            n = value.nextInt(10);
	            start += Integer.toString(n);
	            
	    }
	    System.out.println(generateAccountNumber());

	}
	
	static public long  generateAccountNumber(){
		  String start = "";
	      Random value = new Random();
	      int n = 0;
		    for(int i =0; i < 12;i++)
		    {
		            n = value.nextInt(10);
		            start += Integer.toString(n);
		            
		    }
		    return Long.parseLong(start);
	  }
}

