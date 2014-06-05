package paperexample;
import java.util.*;

// after rewrites, we can infer WList is contravariant
class WList<E> {
	// user selects to generalize this first
	List<E> elems = new LinkedList<E>();
	
	public void add(E elem) {
	  addAll(Collections.singletonList(elem));
	}

	// user selects to generalize source first
	void addAll(List<E> source) {
		WList.addAndLog(source.iterator(), elems);
	}

	// user selects to generalize dest second
	static <T> void addAndLog(Iterator<T> itr, List<T> dest) {
		while(itr.hasNext()) {
			T elem = itr.next();
			// log(elem);
			dest.add(elem);
		}
	}
	
	static void client(WList<String> strings) {
		strings.add("one");
		strings.add("two");
	}
}


class MapEntryWList<K, V> extends WList<Map.Entry<K, V>>
{
  @Override
  public void add(Map.Entry<K, V> entry) {
  	super.add(entry);
  }
}

public class PaperExample
{
	public static void main(String[] args) {
		System.out.println("This is the motivating example from the paper");
		WList<String> wlist = new WList<String>();
		WList.client(wlist);
	}
}
