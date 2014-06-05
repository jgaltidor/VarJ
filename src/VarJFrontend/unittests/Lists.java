package test;
import java.util.*;


class RList<E>
{
	private List<E> elems;
	
	RList() { this(new LinkedList<E>()); }
	
	RList(List<E> elems) { this.elems = elems; }
	
	E get(int index) { return elems.get(index); }
	
	int size() { return elems.size(); }
	
  Iterator<E> iterator() { return elems.iterator(); }
	
	static void test() {
		System.out.println("RList started");
		LinkedList<String> strs = new LinkedList<String>();
		strs.add("one"); strs.add("two");
		RList<String> rlist = new RList<String>(strs);
		for(int i = 0; i < rlist.size(); i++) {
			System.out.printf("rlist.get(%d): %s", i, rlist.get(i));
			System.out.println();
		}
	}
}

class WList<E>
{
	private List<E> elems;
	
	WList(List<E> elems) { this.elems = elems; }
	
	void add(E elem) { elems.add(elem); }
	
	int size() { return elems.size(); }
	
	int size2() { return this.size(); }
	
	// public void doNothing(Comparator<E> comp) {}

	// public <T extends E> void whatever(Iterable<T> itr) { }

	static void test() {
		System.out.println("WList started");
		LinkedList<String> strs = new LinkedList<String>();
		WList<String> wlist = new WList<String>(strs);
		System.out.println("Adding \"one\" to wlist");
		wlist.add("one");
		System.out.println("Adding \"two\" to wlist");
		wlist.add("two");

	}
}


class IList<X>
{
	List<X> elems;
	
	IList(List<X> elems) { this.elems = elems; }
	
	X get(int index) { return elems.get(index); }
	
	void add(X elem) { elems.add(elem); }
	
	int size() { return elems.size(); }
	
	void addFirst() { add(elems.get(0)); }
	
	IList<X> another = null;
	
	void flowsTest(IList<X> ilist1, IList<X> ilist2, IList<X> ilist3) {
		ilist1.elems = this.elems;
		ilist1 = ilist2;
		ilist2.elems = ilist1.elems;
		ilist3.elems = ilist1.elems;
		another = ilist1;
		ilist2 = another;
		ilist2.elems = another.elems;
	}
	
	public static <T> Iterator<T> idItr(Iterator<T> itr) { return itr; }
	
	public static <T> List<T> idList(List<T> list) { return list; }
	
	public Iterator<X> getIterator(IList<X> other) {
		int n = Math.max(IList.idList(other.getElems()).size(), 2) + 1;
		System.out.println("n: " + n);
		return IList.idItr(IList.idItr(getElems().iterator()));
	}
	
	public List<X> getElems() { return elems; }
}


class SpecialIList<E> extends IList<E>
{
	SpecialIList(java.util.List<E> elems) {
		super(elems);
	}

	void add(E elem) {
		System.out.println("elem: " + elem);
		super.add(elem);
	}

	E get(int index) {
		System.out.println("index: " + index);
		return super.get(index);
	}
	
	@Override
	public Iterator<E> getIterator(IList<E> other) {
		E first = elems.get(0);
		return super.getIterator(other);
	}
}


public class Lists
{
	public void whatever(WList<String> ws) {
		System.out.println("just a test");
	}
	
	public Iterator<String> foo(SpecialIList<String> silist, IList<String> other) {
		return silist.getIterator(other);
	}

	public static void main(String[] args) {
		RList.test();
		WList.test();
	}
}


