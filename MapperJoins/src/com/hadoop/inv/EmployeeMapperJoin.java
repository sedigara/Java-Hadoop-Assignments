package com.hadoop.inv;

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


public class EmployeeMapperJoin extends Mapper<LongWritable, Text, Text, Text>{
	Map <String, String> deptMap = new HashMap<String,String>();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String[] words = value.toString().split(",");
		String empDeptId = words[3];
		String deptName = deptMap.get(empDeptId)==null?"NotFound":deptMap.get(empDeptId);
		context.write(value,new Text(deptName));
		
	}
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		File f=new File("dept.txt");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = "";
		try {
			while ((line=reader.readLine())!=null)
			{
				String deptid=line.split(",")[0];
				String deptname = line.split(",")[1];
				deptMap.put(deptid, deptname);
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
