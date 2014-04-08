package Parallel;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class Reducer2 extends MapReduceBase implements Reducer<IntWritable, Text, IntWritable, Text> 
{		
	 
     public void reduce(IntWritable key, Iterator<Text> values, OutputCollector<IntWritable, Text> output, Reporter reporter) throws IOException 
     {
      ArrayList<String> storage = new ArrayList<String>();
      while(values.hasNext())
      {
    	storage.add(values.next().toString());
      }
      StringBuffer sb = new StringBuffer();
      String[] answer = storage.get(0).split("\n");
      int currLen = 0;
 	  for(int i=1;i<storage.size();i++)
      {
 		 String[] line2 = storage.get(i).split("\n");
 		 
 		 Parallel.lcsStructure temp = Parallel.LCS.lcsWithStrings(answer, line2);
 		 answer = temp.lcs;
 		 currLen = temp.len;
      }
 	  Text word = new Text();
 	  
 	  for(int i=0;i<answer.length;i++)
 	  {
 		  sb.append(answer[i]);
 		  sb.append("\n");
 	  }
 	  word.set(sb.toString());
 	  output.collect(new IntWritable(currLen),word);
     }
}

