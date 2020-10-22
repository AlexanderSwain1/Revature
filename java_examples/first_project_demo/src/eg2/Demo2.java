package eg2;

import eg3.Animal;

public class Demo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator.helloStaticCalci();
		
		Animal.helloStaticAnimal1();
		Animal.helloStaticAnimal2();
		
		Calculator c = new Calculator();
		c.NonStatic();
		
		Animal a = new Animal();
		a.Nonstatic();
		
		int result = c.add(100, 20);
		System.out.println("result = " + result);
		
		System.out.println("result = " + c.add(99, 888));
		
		System.out.println("result = " + c.add(99, 888, 17));
		
		System.out.println("result = " + c.add(9.0f, 88.5f));
	}
}
