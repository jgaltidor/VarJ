package test;
import java.util.LinkedList;

interface Animal {
	void speak();
}

class Dog implements Animal
{
	public void speak() {
		System.out.println("woof");
	}
}

class Cat implements Animal
{
	public void speak() {
		System.out.println("meow");
	}
}

class Frog implements Animal
{
	public void speak() {
		System.out.println("ribbit");
	}
}

class Cell<A>
{
	LinkedList<A> list = new LinkedList<A>();
	
	public Cell(A elem) { list.add(elem); }
	
	public LinkedList<A> getList() { return list; }
	
	public void setList(LinkedList<A> newList) {
		list = newList;
	}
}


class Pair<B, C> extends Cell<B>
{
	LinkedList<C> list2 = new LinkedList<C>();
	
	public Pair(B fst, C snd) {
		super(fst);
		list2.add(snd);
	}
	
	public void setList(LinkedList<B> newList) {
		System.out.println("Calling Pair.setList");
		list = newList;
	}
	
	public void setList2(LinkedList<C> newList2) {
		list2 = newList2;
	}
}

public class OverrideTest
{
	public static void foo(int num) {
		Animal a = (num % 2 == 0) ?
			new Dog() : new Cat();
		a.speak();
	}

	public static void main(String[] args)
	{
		if(args.length == 0) {
			System.err.println("usage: java test.Override <num>");
			System.exit(1);
		}
		
		int num = Integer.parseInt(args[0]);
		foo(num);
	}
}

