package eg4;

public class Product {
	String name;
	float cost;
	static float discount = 9.5f;
	
	public static void main(String[] args)
	{
		Product p1 = new Product();
		p1.name = "TV";
		p1.cost = 89.99f;
		System.out.println("Printing p1");
		p1.ptinProduct();
		
		Product p2 = new Product();
		p2.name = "Laptop";
		p2.cost = 189.99f;
		System.out.println("Printing p2");
		p1.ptinProduct();
		
		discount = 4.2f;
		System.out.println("Printing p1 again");
		p1.ptinProduct();
		System.out.println("Printing p2 again");
		p1.ptinProduct();
	}
	
	public void ptinProduct()
	{
		System.out.println("Product Name : " + name);
		System.out.println("Product Cost : " + cost);
		System.out.println("Product Discount : " + discount);
	}
}
