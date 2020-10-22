package mypack;

public class MyFirstClass {

	//lastly the instructor push to git
	//git commit -m "comment"
	//git push
	public static void main(String[] args) {
		Static_NonStatic();
	}
	
	private static void HelloWorld()
	{
		// TODO Auto-generated method stub
		System.out.println("mama, mama, mama.");
		System.out.println("WHAT!!!!");
		System.out.println("Hi.");
	}
	
	/**
	 * datatype			size		defaultValue
	 * byte 			1byte		0
	 * short			2			0
	 * int				4			0
	 * long				8			0
	 * 
	 * float			4			0.0000f
	 * double			8			0.0000d
	 * 
	 * char				2			'\u00000 - https://unicode-table.com/en/#basic-latin
	 * boolean			1bit		false
	 */
	private static void DataTypes()
	{
		int x = 10;
		System.out.println("x = " + x);
		
		float f1 = 12.3f;
		System.out.println("f1 = " + f1);
		
		double d1 = 22.333d;
		System.out.println("d1 = " + d1);
		
		long contact = 1234567899L;
		System.out.println("contact = " + contact);
		
		boolean b = false;
		System.out.println("b = " + b);
		
		//java programmers love to type XD
		//Please note that String is a class not a primitive data type
		String s = "Hello String";
		System.out.println("s = " + s);
		
		char c = 'H';
		System.out.println("c : " + c);
	}
	private static void TypeCasting()
	{
		/*
		 * Type Casting is the way using which you can convert one primitive type to another
		 * 1) implicit - which is done by the java compiler by default, when we are trying to assign
		 * 				 a data type with smaller in size to the larger in size
		 * 2) explicit - which should be taken care b the developers, where we try to assign any larger
		 * 				 data type to the smaller we have to explicitly say to which smaller we wish to convert it to.
		 */
		int x = 100;
		long l = x;
		System.out.println("l = " + l);
		
		double d = x;
		System.out.println("d = " + d);
		
		float f = l; // implicit (decimals are larger than the normal values
		System.out.println("f = " + f);
		
		char c = 'M';
		System.out.println("c : " + c);
		x = c; //implicit
		System.out.println("x : " + x);
		
		//Explicit Casting
		int d1 = 99;
		char z = (char)d1;//Explicit(larger to smaller)
		System.out.println("z : " + z);
		
		float t = 88.77f;
		System.out.println("t = " + t);
		d1 = (int)t; //explicit(remember all the decimal values are larger
	}
	private static void Static_NonStatic()
	{
		System.out.println("Hello");
		
		//static
		StaticNonstatic.myStaticMethod();
		
		//non static
		StaticNonstatic d = new StaticNonstatic();
		d.myNonStaticMethod1();
		d.myNonStaticMethod2();
		
	}
}