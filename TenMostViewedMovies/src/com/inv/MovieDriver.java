package com.inv;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
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
		job.setJarByClass(MovieReducer.class);
		job.setMapperClass(MovieMapper.class);
		job.setReducerClass(MovieReducer.class);
		
		//job input types nad job output types different
		//below lines need to be mentioned
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.addCacheFile(new URI("/home/osboxes/Downloads/ml-1m/movies.dat"));
		FileInputFormat.addInputPath(job, new Path (args [0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		boolean status = job.waitForCompletion(true);
		int result= status?0:1;
		
		System.exit(result);
	}

}
