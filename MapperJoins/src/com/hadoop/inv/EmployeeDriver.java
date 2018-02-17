package com.hadoop.inv;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class EmployeeDriver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
		
		Configuration conf = new Configuration();
		conf.set("mapreduce.output.textoutputformat.separator", ",");//sequence file
		Job job = Job.getInstance(conf,"Word Count");
		job.setJarByClass(EmployeeDriver.class);
		job.setMapperClass(EmployeeMapperJoin.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.addCacheFile(new URI("/home/osboxes/InputFiles/dept.txt"));
		job.setNumReduceTasks(0);
		System.out.println("****Number of reducers ****"+job.getNumReduceTasks());
		FileInputFormat.addInputPath(job, new Path (args [0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		boolean status = job.waitForCompletion(true);
		int result= status?0:1;
		
		System.exit(result);
	}

}
