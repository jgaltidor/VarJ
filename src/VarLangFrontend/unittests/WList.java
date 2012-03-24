package test;
import java.util.*;

/*
interface IndirectList<E> {
	WList<E> getUnderlyingList();
}
*/

public class WList<E>
{
	private List<E> elems;
	
	public WList(List<E> elems) { this.elems = elems; }
	
	public void add(E elem) { elems.add(elem); }
	
	public int size() { return elems.size(); }
	
	// public void doNothing(Comparator<E> comp) {}
	
	// public List<E> blah(WList<E> wl) { return elems; }
	
	// public <T> T identity(T obj) { return obj; }
	

	// public <T extends E> void whatever(Iterable<T> itr) { }

	/*
	public int compareSize(IndirectList<E> indList) {
		int thisSize = elems.size();
		int thatSize = indList.getUnderlyingList().elems.size();
		if(thisSize == thatSize) return 0;
		else if(thisSize < thatSize) return -1;
		else return 1;
	}
	*/
	
	public static void main(String[] args) {
		System.out.println("WList started");
		LinkedList<String> strs = new LinkedList<String>();
		WList<? super String> wlist = new WList<String>(strs);
		System.out.println("Adding \"one\" to wlist");
		wlist.add("one");
		System.out.println("Adding \"two\" to wlist");
		wlist.add("two");

	}
}
