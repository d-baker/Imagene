package imagene.imagegen;

import java.util.Calendar;

/*****************************************
 * Written by Callum McLennan (s3367407) *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

public class Timer 
{
	public static long start()
	{
		return Calendar.getInstance().getTimeInMillis();
	}
	
	public static long stop(long start)
	{
		return Calendar.getInstance().getTimeInMillis() - start;
	}
}
