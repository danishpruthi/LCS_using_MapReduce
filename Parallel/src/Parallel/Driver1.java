package Parallel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

import Parallel.Mapper1;
import Parallel.Reducer1;


public class Driver1 {
	static final int k =2;
	static int counter = 0;
	Driver1(String args) throws IOException {
		
	    JobConf conf = new JobConf(Parallel.Driver1.class);
	    conf.setJobName("MapReduce1");
	    conf.addResource(new Path("path-to-your-core-site.xml file"));
	    conf.addResource(new Path("path-to-your-hdfs-site.xml file"));
	 
	    conf.setOutputKeyClass(IntWritable.class);
	    conf.setOutputValueClass(Text.class);

	    conf.setMapperClass(Mapper1.class);
	    conf.setReducerClass(Reducer1.class);

	    conf.setInputFormat(TextInputFormat.class);
	    conf.setOutputFormat(TextOutputFormat.class);

	    java.nio.file.Path dir= Paths.get(args);
	    //"/home/Danish/input"
		  //File pairFile = new File(dir.getName()+"/pairs.txt");
	      File keyFile = new File("keyFile.txt");
		  PrintWriter pw = new PrintWriter(keyFile); 
		  
		  try (DirectoryStream <java.nio.file.Path> stream = Files.newDirectoryStream((java.nio.file.Path) dir)) 
		  {		  
			  
			  // storing the absolute paths of files in an ArrayList
			  ArrayList<java.nio.file.Path> store = new ArrayList<java.nio.file.Path>();
			  for(java.nio.file.Path file: stream) 
			  { 
				  store.add(file.toAbsolutePath()); 
			  }
			  int len = store.size();
			  for(int i=0;i<len;i++) 
			  {
				  for(int j=i+1;j<len;j++)
					  pw.println(store.get(i).toString()+" "+store.get(j).toString()); 
			  }
			  stream.close();
			  
		} catch (IOException | DirectoryIteratorException x) { System.err.println(x);} 
		  pw.close();
		  
		  FileInputFormat.setInputPaths(conf, keyFile.getAbsolutePath());
		  Path reduce1=new Path("OutputReducerMR1");
		  
		  FileOutputFormat.setOutputPath(conf,reduce1);
		  JobClient.runJob(conf);
		  //driver2(reduce1.getAbsolutePath(), args[1]);
	  }

}
