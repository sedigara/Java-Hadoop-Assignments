package com.hadoop.inv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;

public class MapAnalysis {
	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		a.add("MR");
		a.add("Hadoop");
		a.add("Hive");
		List<Text> b = new ArrayList<Text>();
		b.add(new Text("MR"));
		b.add(new Text("Hadoop"));
		b.add(new Text("Hive"));
		putText(b);
		putString(a);
		
	}
	
	public static  void putText(List<Text> b)
	{
		Map<Text,Integer> map = new HashMap<Text,Integer>();
		for(int i=0;i<3;i++)
		map.put(b.get(i), i);
		 for (Entry<Text ,Integer>  entry : map.entrySet())
		 {
	        System.out.println("Key: "+entry.getKey()+"  Value: "+entry.getValue());
	     }

	}
	
	public static void putString(List<String> a)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i=0;i<3;i++)
		{
			map.put(a.get(i), i);
		}
		for (Entry<String,Integer> entry : map.entrySet())
		{
			System.out.println("Key :" + entry.getKey()+"  Value: "+entry.getValue());
		}
		
	}

}
