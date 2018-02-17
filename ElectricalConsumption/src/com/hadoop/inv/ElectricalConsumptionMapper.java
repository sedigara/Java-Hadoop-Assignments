package com.hadoop.inv;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class ElectricalConsumptionMapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String[] words = value.toString().split(",");
		String year = words[0];
		int min = Integer.parseInt(words[1].toString());
		int max = Integer.parseInt(words[1].toString());
		for (int i=1;i<words.length-1;i++){
			if(Integer.parseInt(words[i].toString())<min )
				min=Integer.parseInt(words[i].toString());
			else if(Integer.parseInt(words[i].toString()) >max ) 
				max = Integer.parseInt(words[i].toString());
			
		}
		context.write(new Text(year),new Text(min+"\t"+max));
		
	}
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		context.write(new Text("YEAR"),new Text("MinTemp	MaxTemps"));
	}
	@Override
	protected void cleanup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.cleanup(context);
	}
}
