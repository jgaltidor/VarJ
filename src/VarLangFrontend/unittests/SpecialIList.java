package test;

public class SpecialIList<E> extends IList<E>
{
	public SpecialIList(java.util.List<E> elems) {
		super(elems);
	}

	public void add(E elem) {
		System.out.println("elem: " + elem);
		super.add(elem);
	}

	public E get(int index) {
		System.out.println("index: " + index);
		return super.get(index);
	}
}
