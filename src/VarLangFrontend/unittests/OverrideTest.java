package test;

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

