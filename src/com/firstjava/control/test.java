package com.firstjava.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class test
{
	public static void main(String[] args)
	{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar c1 = Calendar.getInstance();

	 String strToday = sdf.format(c1.getTime());

        System.out.println("Today=" + strToday);

	}
	
}
