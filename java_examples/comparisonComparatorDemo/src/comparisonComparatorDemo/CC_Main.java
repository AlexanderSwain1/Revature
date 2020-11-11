package comparisonComparatorDemo;

import java.util.ArrayList;
import java.util.List;

public class CC_Main 
{
	public static void Main(String[] args)
	{
		MyIntComparable a = new MyIntComparable(2);
		MyIntComparable b = new MyIntComparable(3);
		
		if (a.compareTo(b) > 0)
			System.out.println("a is greater than b");
		
		List<MyIntComparable> c = new ArrayList<MyIntComparable>();
		c.add(a); c.add(b);
		
		c.sort(new MyIntComparator());
	}
}
