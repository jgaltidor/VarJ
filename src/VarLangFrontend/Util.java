package tame;
import AST.*;

/** Needed to added some utility methods to this class because JastAdd
  * fails to parse some legal Java methods, such as generic methods
  * (methods with declared type parameters).
  */
public class Util
{
	public static <T extends ASTNode> List<T> toWildList(List<T> list)
	{
		List<T> newList = new List<T>();
		for(int i = 0; i < list.getNumChild(); i++) {
			// (T) is an unchecked cast but this is safe
			newList.add((T) list.getChild(i).toWild());
		}
		return newList;
	}

	public static <T extends Access> List<T> toWildAccessList(ASTNode node, List<T> list)
	{
		List<T> newList = new List<T>();
		for(int i = 0; i < list.getNumChild(); i++) {
			newList.add((T) node.toWildAccess(list.getChild(i)));
		}
		return newList;
	}
	
	public static <T extends ASTNode> Opt<T> toWildOpt(Opt<T> opt) {
		return (opt.getNumChild() != 0) ?
			new Opt<T>((T) opt.getChild(0).toWild()) : opt;
	}

	public static <T extends Access> Opt<T> toWildAccessOpt(ASTNode node, Opt<T> opt) {
		return (opt.getNumChild() != 0) ?
			new Opt<T>((T) node.toWildAccess(opt.getChild(0))) : opt;
	}
}

