package test;
import java.util.*;

class BodyTestCovar<E>
{	
	public void printFirst(List<E> list) {
		System.out.println("list.get(0): " + list.get(0));
	}
}


class BodyTestBivar<E>
{	
	public void printSize(List<E> list) {
		System.out.println("list.size(): " + list);
	}
}

class BodyTestContravar<E>
{
	private E elem = null;

	public boolean addElemTo(List<E> list) {
		return list.add(this.elem);
	}
}

class BodyTestInvar<E>
{
	public boolean readAndWrite(List<E> list) {
		E firstElem = list.get(0);
		return list.add(firstElem);
	}
}


public class MBodyTest
{	
	public static void main(String[] args) {
		System.out.println("tests for method body analysis");
	}
}
