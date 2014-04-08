package Parallel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class Mapper1 extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, Text>  
{
	IntWritable one = new IntWritable(1);
    Text word = new Text();
    String s1, s2;
	public void map(LongWritable key, Text value, OutputCollector<IntWritable,Text> output, Reporter reporter) throws IOException 
	{
		    String[] line = value.toString().split(" "); // separating from a delimiter
	        java.nio.file.Path p1= Paths.get(line[0]);
	   	    java.nio.file.Path p2= Paths.get(line[1]);
	    	Parallel.lcsStructure temp= Parallel.LCS.lcsWithPath(p1,p2);
	   	    
	    	word.set(line[0]+" "+line[1]);
	   	    // here the key selected is negative of LCS!! so that when it arranges in ascending order 
	    	output.collect(new IntWritable(-1*temp.len),word);
	}
}


