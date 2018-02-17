package com.hadoop.inv;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordsCountDriver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		Configuration conf = new Configuration();
		Job job = new Job(conf,"Word Count");
		int value = Integer.MIN_VALUE;
		
		job.setJarByClass(WordsCountDriver.class);
		job.setMapperClass(WordsCountMapper.class);
		job.setReducerClass(WordsCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//job.setPartitionerClass(WordsPartitioner.class);
		//conf.set("fs.default.name", "hdfs://localhost:9000");
		//conf.set("mapred.job.tracker", "localhost:9001");

		job.setNumReduceTasks(3);
		System.out.println("****Number of reducers ****"+job.getNumReduceTasks());
        job.setPartitionerClass(WordsPartitioner.class);
		FileInputFormat.addInputPath(job, new Path (args [0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		boolean status = job.waitForCompletion(true);
		int result= status?0:1;
		
		System.exit(result);
	}

}
