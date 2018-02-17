package com.inv;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class MovieDriver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
		//jarfile  entryclass inputfilepath outputfilepath 
		//Job object depends on Configuration
		//get configuration object
		FileUtil.fullyDelete(new File(args[1]));
		Configuration conf = new Configuration();
		conf.set("mapreduce.output.textoutputformat.separator", "::");
		Job job = Job.getInstance(conf,"Movie Count");
		job.setJarByClass(MovieDriver.class);
		job.setMapperClass(MovieRatingsMapper.class);
		job.setReducerClass(MovieReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, new Path (args [0]));
		//MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, MovieUserMapper.class);
		//MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, MovieRatingsMapper.class);
		
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		boolean status = job.waitForCompletion(true);
		int result= status?0:1;
		
		if (status)
		{
			Job userJob = Job.getInstance(conf,"Users Count");
			userJob.setJarByClass(MovieDriver.class);
			userJob.setMapperClass(UsersMapperJoin.class);
			userJob.setReducerClass(UsersReducer.class);
			userJob.setOutputKeyClass(Text.class);
			userJob.setOutputValueClass(DoubleWritable.class);
			userJob.setMapOutputKeyClass(Text.class);
			userJob.setMapOutputValueClass(Text.class);
			userJob.addCacheFile(new URI(args[1]+"/part-r-00000"));
			FileInputFormat.addInputPath(job, new Path (args [2]));
			FileOutputFormat.setOutputPath(job, new Path(args[3]));
			//MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, MovieUserMapper.class);
			//MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, MovieRatingsMapper.class);
			
		}
		
		System.exit(result);
	}

}
