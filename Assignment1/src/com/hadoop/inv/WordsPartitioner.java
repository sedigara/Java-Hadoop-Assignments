package com.hadoop.inv;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class WordsPartitioner extends Partitioner<Text, IntWritable>{

	@Override
	public int getPartition(Text key, IntWritable value, int numReduceTasks) {
		// TODO Auto-generated method stub
		System.out.println("In Partitioner-------------------"+numReduceTasks);
		if(numReduceTasks==3){
		if ((key.toString().charAt(0) >=65 && key.toString().charAt(0) <= 78 ) ||
				(key.toString().charAt(0) >= 97 && key.toString().charAt(0) <=110)){
			System.out.println(key.toString()+"word is moving to file 0");
			return 0;
		}
		else if(( key.toString().charAt(0) >= 79 && key.toString().charAt(0) <=90) || 
				( key.toString().charAt(0) >= 111 && key.toString().charAt(0) <=122)){
			System.out.println(key.toString()+"word is moving to file 1");
				return 1;
					
				}
		else {
			System.out.println(key.toString()+"word is moving to file 2");
			return 2;
		}
		}
		else
		{
			  System.err.println("WordCountParitioner can only handle either 1 or 2 paritions");
	            return 0;
		}
	}
	

}
