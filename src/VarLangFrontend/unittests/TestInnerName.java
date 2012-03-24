package test;

public class TestInnerName
{
	public String name;
	test.TestInnerName.InnerAge iage;

	public TestInnerName(String n, int a ) {
		name = n;
		iage = new TestInnerName.InnerAge(a);
	}
	
	public static class InnerAge {
		int age;
		public InnerAge(int a) { age = a; }
		
		public test.TestInnerName.InnerAge makeCopy() {
			return new test.TestInnerName.InnerAge(age);
		}
	}
	
	public static void main(String args[]) {
		System.out.println("It works!");
	}
}
