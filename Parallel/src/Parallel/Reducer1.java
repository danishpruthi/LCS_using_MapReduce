package Parallel;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class Reducer1 extends MapReduceBase implements Reducer<IntWritable, Text, IntWritable, Text> 
{
     public void reduce(IntWritable key, Iterator<Text> values, OutputCollector<IntWritable, Text> output, Reporter reporter) throws IOException 
     {
      while(values.hasNext())
      {
    	if(Driver1.counter<Driver1.k)
    	  {
    	  output.collect(new IntWritable(key.get()*(-1)), values.next());
    	  Driver1.counter++;
    	  }
    	  else values.next();
      }
     
     }
}

