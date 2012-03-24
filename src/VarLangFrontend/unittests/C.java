package test;

public interface C<X>
{
	X foo(C<? super X> csx);
	void bar(D<? extends X> dsx);
}
