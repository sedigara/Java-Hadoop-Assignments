package com.hadoop.inv;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class WordsPartitioner extends Partitioner<Text, IntWritable>{

	@Override
	public int getPartition(Text key, IntWritable value, int numReduceTasks) {
		// TODO Auto-generated method stub
		int small=65;
		int capital = 97;
		int count=0;
		System.out.println("In Partitioner-------------------"+numReduceTasks);
		if(numReduceTasks==3){
			for (int i=65,j=97;i<=90&&j<=122;i++,j++){
				if(i != 65 && j!=97) count++;
				if (key.toString().charAt(0) ==i || key.toString().charAt(0) ==j )
					return count;
				
			}
		
			System.out.println(key.toString()+"word is moving to file 2");
			return 27;
	
		}
		else
		{
			  System.err.println("WordCountParitioner can only handle either 1 or 2 paritions");
	            return 0;
		}
	}
	

}
