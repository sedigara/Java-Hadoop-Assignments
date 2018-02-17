package com.inv;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		String line  = value.toString();
		StringTokenizer words= new StringTokenizer(line , " ");
		while (words.hasMoreTokens()) {
			String word = words.nextToken();
			context.write(new Text(word),new IntWritable(1));
		}
	}
	
	
	
	

}
