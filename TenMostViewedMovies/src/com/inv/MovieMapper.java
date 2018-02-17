package com.inv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MovieMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	Map <String, String> movieMap = new HashMap<String,String>();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		String line  = value.toString();
		String [] words=  line.split("::");
		String movieName = movieMap.get(words[1])==null?"NotFound":movieMap.get(words[1]);
			context.write(new Text(words[1]+"::"+movieName),new IntWritable(1));
	}
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		File f=new File("movies.dat");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = "";
		try {
			while ((line=reader.readLine())!=null)
			{
				String movieId=line.split("::")[0];
				String movename = line.split("::")[1];
				movieMap.put(movieId,movename);
				}
			}
		finally{
			if(reader!=null){
				reader.close();
			}
		}
	}
	
	

}
