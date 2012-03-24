package test;
import java.util.*;

public class RList<E>
{
	private List<E> elems;
	
	public RList(List<E> elems) { this.elems = elems; }
	
	public E get(int index) { return elems.get(index); }
	
	public int size() { return elems.size(); }
	
	public static void main(String[] args) {
		System.out.println("RList started");
		LinkedList<String> strs = new LinkedList<String>();
		strs.add("one"); strs.add("two");
		RList<? extends String> rlist = new RList<String>(strs);
		for(int i = 0; i < rlist.size(); i++) {
			System.out.printf("rlist.get(%d): %s", i, rlist.get(i));
			System.out.println();
		}
	}
}
