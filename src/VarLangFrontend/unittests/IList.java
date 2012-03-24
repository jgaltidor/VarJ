package test;
import java.util.*;

public class IList<E>
{
	private List<E> elems;
	
	public IList(List<E> elems) { this.elems = elems; }
	
	public E get(int index) { return elems.get(index); }
	
	public void add(E elem) { elems.add(elem); }
	
	public int size() { return elems.size(); }
	
	public static void main(String[] args) {
		System.out.println("IList started");
		IList<String> ilist = new IList<String>(new LinkedList<String>());
		System.out.println("Adding \"one\" to ilist");
		ilist.add("one");
		System.out.println("Adding \"two\" to ilist");
		ilist.add("two");
		for(int i = 0; i < ilist.size(); i++) {
			System.out.printf("ilist.get(%d): %s", i, ilist.get(i));
			System.out.println();
		}
	}
}
