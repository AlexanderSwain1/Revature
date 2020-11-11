package comparisonComparatorDemo;

import java.util.Comparator;

public class MyIntComparator implements Comparator<MyIntComparable>
{

	@Override
	public int compare(MyIntComparable arg0, MyIntComparable arg1) 
	{
		return arg0.compareTo(arg1);
	}

}
