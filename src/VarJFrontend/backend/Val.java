package backend;

public enum Val
{
	COVARIANT("+"),
	CONTRAVARIANT("-"),
	BIVARIANT("*"),
	INVARIANT("o");
	
	public final String notation;
	
	Val(String notation) { this.notation = notation; }

	public String toString() { return notation; }

	// Comparison order methods

	/** Similar to v1.compareTo(other) except
	  * returns -2 if comparison is undefined.
	  */
	public int trycompareTo(Val other) {
		switch(this) {
			case BIVARIANT:
				switch(other) {
					case BIVARIANT: return 0;
					default: return 1;
				}
			case INVARIANT:
				switch(other) {
					case INVARIANT: return 0;
					default: return -1;
				}
			case COVARIANT:
				switch(other) {
					case BIVARIANT: return -1;
					case COVARIANT: return 0;
					case INVARIANT: return 1;
					case CONTRAVARIANT: return -2;
				}
			case CONTRAVARIANT:
				switch(other) {
					case BIVARIANT: return -1;
					case CONTRAVARIANT: return 0;
					case INVARIANT: return 1;
					case COVARIANT: return -2;
				}
		}
		throw new IllegalStateException("Should never reach here");
	}

	public boolean equals(Val other) {
		return this == other;
	}

	public boolean gt(Val other) {
		return trycompareTo(other) == 1;
	}

	public boolean lt(Val other) {
		return trycompareTo(other) == -1;
	}

	public boolean gteq(Val other) {
		return equals(other) || gt(other);
	}

	public boolean lteq(Val other) {
		return equals(other) || lt(other);
	}

	/** Implements transform operator definition. */
	public Val transform(Val other) {
		switch(this) {
			case BIVARIANT: return BIVARIANT;
			case INVARIANT: return INVARIANT; // non-commutative choice
			case COVARIANT: return other;
			case CONTRAVARIANT:
				switch(other) {
					case COVARIANT:     return CONTRAVARIANT;
					case CONTRAVARIANT: return COVARIANT;
					case BIVARIANT:     return BIVARIANT;
					case INVARIANT:     return INVARIANT;
				}
		}
		throw new IllegalStateException("Should never reach here");
	}
	
	// binary operators

	public Val meet(Val other) {
		switch(this) {
			case BIVARIANT: return other;
			case INVARIANT: return INVARIANT;
			case COVARIANT:
				switch(other) {
					case COVARIANT:     return COVARIANT;
					case BIVARIANT:     return COVARIANT;
					case CONTRAVARIANT: return INVARIANT;
					case INVARIANT:     return INVARIANT;
				}
			case CONTRAVARIANT:
				switch(other) {
					case CONTRAVARIANT: return CONTRAVARIANT;
					case BIVARIANT:     return CONTRAVARIANT;
					case COVARIANT:     return INVARIANT;
					case INVARIANT:     return INVARIANT;
				}
		}
		throw new IllegalStateException("Should never reach here");
	}
	
	public Val join(Val other) {
		switch(this) {
			case BIVARIANT: return BIVARIANT;
			case INVARIANT: return other;
			case COVARIANT:
				switch(other) {
					case BIVARIANT:     return BIVARIANT;
					case CONTRAVARIANT: return BIVARIANT;
					case COVARIANT:     return COVARIANT;
					case INVARIANT:     return COVARIANT;
				}
			case CONTRAVARIANT:
				switch(other) {
					case BIVARIANT:     return BIVARIANT;
					case COVARIANT:     return BIVARIANT;
					case CONTRAVARIANT: return CONTRAVARIANT;
					case INVARIANT:     return CONTRAVARIANT;
				}
		}
		throw new IllegalStateException("Should never reach here");
	}
}


