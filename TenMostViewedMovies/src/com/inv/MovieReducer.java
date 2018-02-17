package com.inv;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.google.common.collect.Iterables;

public class MovieReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	Map <String,Integer>  map = new HashMap<String,Integer>();
@Override
protected void reduce(Text key, Iterable<IntWritable> values,
		Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable value : values) {
				sum = value.get()+sum;	
			}
			map.put(key.toString(), sum);
	}
@Override
protected void cleanup(Context context)
		throws IOException, InterruptedException {
	// TODO Auto-generated method stub
	int count =0;
	Map<String ,Integer> sorted_map = sort(map);
	 for (Entry<String, Integer> entry : sorted_map.entrySet())
	 {
		 //count++;
         context.write( new Text(entry.getKey()),new IntWritable(entry.getValue()));
         //if (count==10) break;
     }

}

public Map<String,Integer> sort(Map<String,Integer> map)
{
	
    List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());

    // Sorting the list based on values
    Collections.sort(list, new Comparator<Entry<String,Integer>>()
    {
        public int compare(Entry<String, Integer> o1,
                Entry<String, Integer> o2)
        {
                return o2.getValue().compareTo(o1.getValue());

        }
    });

    // Maintaining insertion order with the help of LinkedList
    Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
    for (Entry<String, Integer> entry : list)
    {
        sortedMap.put(entry.getKey(), entry.getValue());
    }

    return sortedMap;
}

}
