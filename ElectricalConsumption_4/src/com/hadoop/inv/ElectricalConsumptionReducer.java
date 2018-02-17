package com.hadoop.inv;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class ElectricalConsumptionReducer  extends Reducer<IntWritable, Text, IntWritable, Text>{
	String maxyear = "";
	String minyear = "";
	IntWritable max = new IntWritable(-1);
	int min=999999999;
	@Override
	protected void reduce(IntWritable key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
		Iterator<Text> it = values.iterator();
		List<Text> cache = new ArrayList<Text>();

		// first loop and caching
		while (it.hasNext()) {
		   Text value = it.next();
		   cache.add(value);
		}
		
		  if (key.compareTo(max) > 0) {
			  maxyear="";
		    max.set(key.get());
		    for (Text value :cache)
		    {
		    	System.out.println( value.toString() );	
		    	maxyear=maxyear+" "+value.toString();
		    }
		  }
		    System.out.println("Chceking minimum for "+key.get() + " min value is "+min);
		    if (min>key.get()) {
				  minyear="";
			    min=key.get();
			    for (Text value :cache)
			    {
			    	System.out.println( value.toString() );	
			    	minyear=minyear+" "+value.toString();
			    }
		    // copy 'values' somewhere
		  }
	
		
		
		
	}
	
	@Override
	protected void cleanup(Reducer<IntWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException { //create output from saved max values;
		  context.write(max,new Text (maxyear));
		  context.write(new IntWritable(min),new Text (minyear));
		}


	

}
