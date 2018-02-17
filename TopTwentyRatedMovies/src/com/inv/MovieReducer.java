package com.inv;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MovieReducer extends Reducer<Text, Text, Text, DoubleWritable> {
	Map <Text,Text>  userMap = new HashMap<Text,Text>();
	Map <String,Double>  ratingMap = new HashMap<String,Double>();
@Override
protected void reduce(Text key, Iterable<Text> values,
		Reducer<Text, Text, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
			double sum = 0;
			double avg=0;
			int size=0;
				for (Text value : values){
					size++;
					sum = sum + Double.parseDouble(value.toString());
				}
				avg = sum/size;
				if(size>40){
					ratingMap.put(key.toString(), avg);
					}
				else{
					System.out.println(key + " is not inclded in out put as the numberof users are "+size);
				}
	}


@Override
protected void cleanup(Context context)
		throws IOException, InterruptedException {
	// TODO Auto-generated method stub
	int count =0;
	Map<String ,Double> sorted_map = sort(ratingMap);
	 for (Entry<String ,Double>  entry : sorted_map.entrySet())
	 {
		 count++;
         context.write( new Text(entry.getKey()), new DoubleWritable(entry.getValue()));
         if (count==20) break;
     }

}

public Map<String ,Double>  sort(Map<String ,Double>  map)
{
	
    List<Entry<String ,Double> > list = new LinkedList<Entry<String ,Double> >(map.entrySet());

    // Sorting the list based on values
    Collections.sort(list, new Comparator<Entry<String,Double>>()
    {
        public int compare(Entry<String, Double> o1,
                Entry<String, Double> o2)
        {
                return o2.getValue().compareTo(o1.getValue());

        }
    });

    // Maintaining insertion order with the help of LinkedList
    Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
    for (Entry<String, Double> entry : list)
    {
        sortedMap.put(entry.getKey(), entry.getValue());
    }

    return sortedMap;
}

}
