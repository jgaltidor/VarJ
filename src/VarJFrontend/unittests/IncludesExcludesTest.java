package test;
import java.util.*;

public class IncludesExcludesTest
{
	public static Iterator<String> foo1(List<String> list) {
		Iterator<String> itr = list.iterator();
		String firstString = itr.next();
		System.out.println("firstString: " + firstString);
		return itr;
	}

	public static Iterator<String> foo2(List<String> list) {
		Iterator<String> itr = list.iterator();
		String firstString = itr.next();
		System.out.println("firstString: " + firstString);
		return itr;
	}

	public static Iterator<String> foo3(List<String> list) {
		Iterator<String> itr = list.iterator();
		String firstString = itr.next();
		System.out.println("firstString: " + firstString);
		return itr;
	}

	public static Iterator<String> foo4(List<String> list) {
		Iterator<String> itr = list.iterator();
		String firstString = itr.next();
		System.out.println("firstString: " + firstString);
		return itr;
	}
}
