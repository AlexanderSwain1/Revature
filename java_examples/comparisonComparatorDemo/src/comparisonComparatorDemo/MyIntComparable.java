package comparisonComparatorDemo;

public class MyIntComparable implements Comparable<MyIntComparable>
{
	int value;
	
	public MyIntComparable(int value)
	{
		this.value = value;
	}

	@Override
	public int compareTo(MyIntComparable arg0) 
	{
		return value - arg0.value;
	}


}
