package Parallel;
import java.io.*;
import java.nio.*;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
public class Driver2 
{
	Driver2() throws IOException
	{
    JobConf conf = new JobConf(Parallel.Driver1.class);
    conf.setJobName("MapReduce2");
    
    conf.addResource(new Path("path-to-your-core-site.xml file"));
    conf.addResource(new Path("path-to-your-hdfs-site.xml file"));
    
    conf.setOutputKeyClass(IntWritable.class);
    conf.setOutputValueClass(Text.class);

    conf.setMapperClass(Mapper2.class);
    
    conf.setReducerClass(Reducer2.class);

    conf.setInputFormat(TextInputFormat.class);
    conf.setOutputFormat(TextOutputFormat.class);
    File outputFile = new File("OutputReducerMR1/part-00000");
	  
    FileInputFormat.setInputPaths(conf, outputFile.getAbsolutePath());
	Path reduce1=new Path("OutputReducerMR2");
	  
    FileOutputFormat.setOutputPath(conf, reduce1 );
	  JobClient.runJob(conf);
	  //driver2(reduce1.getAbsolutePath(), args[1]);
  }

}
