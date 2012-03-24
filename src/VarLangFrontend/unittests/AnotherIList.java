import java.util.List;

public class AnotherIList<E>
{
	public List<E> elems;
	
	public AnotherIList(List<E> elems) { this.elems = elems; }
	
	public void whatever(List<String> arg) { System.out.println("do nothing"); }
}
