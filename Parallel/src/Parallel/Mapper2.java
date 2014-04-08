package Parallel;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.*;

public class Mapper2 extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, Text>  
{
	
    Text word = new Text();
 
	public void map(LongWritable key, Text value, OutputCollector<IntWritable,Text> output, Reporter reporter) throws IOException 
	{		
			System.out.println(value.toString());
			String dummy[] = value.toString().split("\t");
			
		    String[] line = dummy[1].split(" ");
	        java.nio.file.Path p1= Paths.get(line[0]);
	   	    java.nio.file.Path p2= Paths.get(line[1]);
	   	    System.out.println(line[0]);
	   	    System.out.println(line[1]);
	    	Parallel.lcsStructure temp = Parallel.LCS.lcsWithPath(p1, p2);
	    	String t[] = temp.lcs;
	    	StringBuffer sb = new StringBuffer();
	    	for(int i=0;i<t.length;i++)
	    	{
	    		sb.append(t[i]);
	    		sb.append("\n");
	    	}
	   	    word.set(sb.toString()); //will get sorted by lcs.*/
	    	output.collect(new IntWritable(5),word);
	    	// any common key
	}
}

