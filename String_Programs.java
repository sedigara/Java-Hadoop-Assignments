

1) Program to revers the String in different approaches.

package com.suppi.practice;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

//refernce:http://javahungry.blogspot.com/2014/12/5-ways-to-reverse-string-in-java-with-example.html
public class String_reverse {
@SuppressWarnings("null")
public static void main(String []args)
{
	//Approach 1: Usinf StringBuilder
	StringBuilder str=new StringBuilder("Deloitte");
	str.reverse();
	System.out.println(str);
	//End
	
	//Approach 2: Using Character array
	@SuppressWarnings("resource")
	Scanner in= new Scanner(System.in);
	System.out.println("enter the string");
	String str1=in.next();
	char [] a= str1.toCharArray();
	int j=0;
	char reverse []= new char[str1.length()];
	
	for (int i=str1.length()-1; i>=0;i--)
	{
		reverse[j]=a[i];
		j++;
	}
	System.out.println(reverse);
	String s=reverse.toString();
	//END
	
	//Approach 3: Using Linked List and Collections.reverse()
	String str11="Hi World";
	List<Character> t=new LinkedList<>();
	char[] hello=str11.toCharArray();
	for (char c:hello)
	{
		t.add(c);
	}
	Collections.reverse(t);
	ListIterator<Character> list=t.listIterator();
	while(list.hasNext())
	{
		System.out.print(list.next());
	}
	System.out.println("\n");	
	
	//Approach 4:Using byte Arrya
	String str111="Hi world";
	byte [] strasbytearry=str111.getBytes();
	byte [] reverse1=new byte[str111.length()];
	for (int i=0;i<str111.length();i++)
	{
		reverse1[i]=strasbytearry[strasbytearry.length-i-1];
	}
	System.out.println(new String(reverse1));	
	}
}



2) Program to print the count of each character in String
package com.string.practice;

import java.util.HashMap;
import java.util.Map;

public class String_duplicate_char {
	
	public static void main(String []args)
	{
		System.out.println("JAVA Program to print the duplicate character in string");
		String str="JAVA PROGRAM HI";
		
		method1(str);
	}
	public static void method1(String s)
	{
		Map<Character,Integer> map=new HashMap<Character,Integer>();
		for(int i=0;i<s.length();i++)
		{
			if(map.containsKey(s.charAt(i)))
			{
				map.put(s.charAt(i),map.get(s.charAt(i))+1);
				
			}
			else
			{
				map.put(s.charAt(i), 1);
			}
		}
		System.out.println(map);
	}
} 


3) Program to sort the string based on length.
  package com.string.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Sort_onlength {

	public static void main(String [] args) 
	{
		
		String [] str= {"hi","hello","how","are","you"};
		System.out.println("Sorting the strings based on length");
		based_onkey(str);
		based_onvalues(str);
		
	}
	public static void based_onkey(String [] str)
	{
		
		System.out.println("Based on Strings in map");
		Map<String,Integer> map=new HashMap<String,Integer>();
		for(String s:str){
			map.put(s, s.length());
		}
		
		Map<String,Integer> tmap=new TreeMap<String,Integer>(map);
		@SuppressWarnings("rawtypes")
		Set set=tmap.entrySet();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Iterator it=set.iterator();
		while(it.hasNext())
		{
			@SuppressWarnings("rawtypes")
			Map.Entry me=(Map.Entry)it.next();
			System.out.print(me.getKey()+":");
			System.out.println(me.getValue());
		}
		
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void based_onvalues(String [] str)
	{
		System.out.println("Based on length of strings in map");
		Map<String,Integer> map=new HashMap<String,Integer>();
		for(String s:str){
			map.put(s, s.length());
		}
		
		Object[] a = map.entrySet().toArray();
		Arrays.sort(a, new Comparator() {
		    public int compare(Object o1, Object o2) {
		        return ((Map.Entry<String, Integer>) o2).getValue()
		                   .compareTo(((Map.Entry<String, Integer>) o1).getValue());
		    }
		});
		for (Object e : a) {
		    System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
		            + ((Map.Entry<String, Integer>) e).getValue());
		}
	}

}


4) Program to check the String is palindrome or not.
package com.string.practice;

import java.util.Scanner;
public class Palindrome {
	public static void main (String []args)
	{
		System.out.println("Enter the String");
		Scanner scan=new Scanner(System.in);
		String str=scan.next();
		palindrome_mathod1(str);

		
		
	}
	/**
	 * @param str
	 */
	public static void palindrome_mathod1(String str)
	{
		StringBuilder st=new StringBuilder(str);
		st.reverse().toString();
		if(str.equals(st.toString()))
		{
			System.out.println(str+" String is PALINDROME");
		}
		else 
			System.out.println(str+" String is not PALINDROME");
	}
	
}


5)Program to check the String containing unique character or not.
package com.suppi.practice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
public class String_unique {
	public static void main(String args[])
	{
		String str="Live like a king";
		//Approach 1:
		/*int flag=0;
		int count=0;
		char [] a=str.toCharArray();
		for(int i=0;i<str.length();i++)
		{
			for(int j=i;j<str.length();j++)
			{
				if(a[i]==a[j])
				{  
					count++;
					if(count>1)
				     {flag=1;
				      break;}
				}
			}
			if(flag==1)
			break;
		}
		if (flag==1)
		{
			System.out.println("The string does not contains the unique character");
			
		}
		else
			System.out.println("The String contains the unique characters");*/
	//End
		
		//Approach 2: USING HASH SET
	/*	boolean r=false;
		
		HashSet<Character> h=new HashSet<Character>();
		for (int i=0;i<str.length();i++)
		{
			 r=h.add(str.charAt(i));
			if (r == false )
				break;
				
		}
		System.out.println(r);*/
		
			System.out.println("Result is "+method4(str));
	}
	
	@SuppressWarnings("unchecked")
	public static boolean method3(String input)
	{
		
		ArrayList a=new ArrayList();
		for (int i=0;i<input.length();i++)
		{ // Live like a king
			int j=input.indexOf(input.charAt(i));
			a.add(j);
			
		}
		Collections.sort(a);
		System.out.println(a);
		for (int i=0;i<(a.size()-1);i++)
		{
			if(a.get(i)==a.get(i+1))
				return false;

		}
		return true;
	}
	
	public static boolean method4(String input )
	{
		boolean r=false;
		for(char c:input.toCharArray())
		{
			if(input.indexOf(c)==input.lastIndexOf(c))
			r=true;
			else
			{
				r=false;
			    break;	
			}
			
		}
		return r;
	}
}



































