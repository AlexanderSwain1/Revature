package eg2;

public class Calculator {
	public static void helloStaticCalci()
	{
		System.out.println("Don't know what was here");
	}
	public void NonStatic()
	{
		System.out.println("Nonstatic Calc");
	}
	
	public int add(int x, int y)
	{
		return x + y;
	}
	public int add(int x, int y, int z)
	{
		return x + y + z;
	}
	public float add(float x, float y)
	{
		return x + y;
	}
}
