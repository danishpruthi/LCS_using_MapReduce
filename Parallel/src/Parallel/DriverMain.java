package Parallel;

import java.io.IOException;

public class DriverMain{
public static void main(String args[]) throws IOException
{	
	System.out.println(args[0]);
	Driver1 dummy1 = new Driver1(args[0]);
	Driver2 dummy2 = new Driver2();
}
}