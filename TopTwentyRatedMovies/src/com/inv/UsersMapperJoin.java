package com.inv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class UsersMapperJoin extends Mapper<LongWritable, Text, Text, Text>{
	List<String> movieIds = new ArrayList<String>();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String[] words = value.toString().split("::");
		if(movieIds.contains(words[1]));
		context.write(value,new Text(deptName));
		
	}
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		File f=new File("part-r-00000");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = "";
		try {
			while ((line=reader.readLine())!=null)
			{
				movieIds.add(line.split("::")[0]);
				}
			}
		finally{
			if(reader!=null){
				reader.close();
			}
		}
	}
	@Override
	protected void cleanup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.cleanup(context);
	}
}
