package test;

interface C<X>
{
	X foo(C<? super X> csx);
	void bar(D<? extends X> dsx);
}

interface D<Y>
{
	void baz(C<Y> cs);
}

public class PLDITest
{
	public static void main(String[] args) {
		System.out.println("Executed test.PLDITest successfully");
	}
}
