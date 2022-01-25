package codeFunctions;

import java.util.concurrent.TimeUnit;

//basic stuff for ease of coding
public class Basics{
	
	//printer
	public static Printer p=new Printer();
	
	//reader
	public static Reader r=new Reader();
	
	//string parser
	public static StringParser sp=new StringParser();
	
	//char utilities
	public static CharUtil cu=new CharUtil();
	
	//math functions
	public static Maths m=new Maths();
	
	//StackOverflowError
	public static void stackOverflowError()
	{
		stackOverflowError();
	}
	
	//waits for given amount of time
	public static void wait(int time)
		throws InterruptedException
	{
		TimeUnit.MILLISECONDS.sleep(time);
	}

}