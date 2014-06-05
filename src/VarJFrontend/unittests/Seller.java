package test;
import java.util.*;

// Testing Inner Class variance analysis

public class Seller<P>
{
	String name;
	Inventory items;
	public final Seller<P> myself = this;
	
	public class Inventory extends RList<P>
	{
		private Seller<P> owner = Seller.this;
	}
	
	public static class Pair<A,B>
	{
		public final A fst;
		public final B snd;
		
		public Pair(A fst, B snd)
		{
			this.fst = fst;
			this.snd = snd;
		}
	}
	
	public void printInventory(
		Seller<String> seller, Pair<Integer, String> other)
	{
		RList<String> list = seller.new Inventory();
		System.out.println("Inner classes are covariant");
	}
	
	public static void printStrings(List<? extends String> list) {
		System.out.println('[');
		for(String str : list) {
			System.out.println(str + ", ");
		}
	}
	
	public static void main(String[] args) {
		
	}
}
