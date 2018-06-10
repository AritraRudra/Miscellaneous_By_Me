/**
 * 
 */
package immutable;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <ol>
 * <li>Don't provide "setter" methods — methods that modify fields or objects referred to by fields.</li>
 * <li>Make all fields <code>final</code> and <code>private</code>.</li>
 * <li>Don't allow subclasses to override methods. The simplest way to do this is to declare the class as
 * <code>final</code>. A more sophisticated approach is to make the constructor <code>private</code> and construct
 * instances in factory methods.</li>
 * <li>If the instance fields include references to mutable objects, don't allow those objects to be changed:
 * <ul>
 * <li>Don't provide methods that modify the mutable objects.</li>
 * <li>Don't share references to the mutable objects. Never store references to external, mutable objects passed to the
 * constructor; if necessary, create copies, and store references to the copies. Similarly, create copies of your
 * internal mutable objects when necessary to avoid returning the originals in your methods.</li>
 * </ul>
 * </li>
 * </ol>
 * 
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html<br>
 * https://dzone.com/articles/how-to-create-an-immutable-class-in-java<br>
 * https://howtodoinjava.com/core-java/basics/how-to-make-a-java-class-immutable/<br>
 * </br>
 * 
 * 
 * Below excetpts are taken from the extremely useful Effective Java 3rd Edition : Item 17: Minimize mutability. All
 * credits to the legendary <a href="https://en.wikipedia.org/wiki/Joshua_Bloch">Joshua Bloch.</a><br>
 * </br>
 * <ol>
 * <li><b>Don’t provide methods that modify the object’s state.</b></li>
 * <li><b>Ensure that the class can’t be extended :</b> This prevents careless or malicious subclasses from compromising
 * the immutable behavior of the class by behaving as if the object’s state has changed. Preventing subclassing is
 * generally accomplished by making the class final.</li>
 * <li><b>Make all fields final.</b></li>
 * <li><b>Make all fields private :</b> This prevents clients from obtaining access to mutable objects referred to by
 * fields and modifying these objects directly. While it is technically permissible for immutable classes to have public
 * final fields containing primitive values or references to immutable objects, it is not recommended because it
 * precludes changing the internal representation in a later release</li>
 * <li><b>Ensure exclusive access to any mutable components :</b> If your class has any fields that refer to mutable
 * objects, ensure that clients of the class cannot obtain references to these objects. Never initialize such a field to
 * a clientprovided object reference or return the field from an accessor. Make defensive copies in constructors,
 * accessors, and readObject methods.</li>
 * </ol>
 * 
 * 
 * <b>Characteristics of immutable objects :</b>
 * <li>Immutable objects are inherently thread-safe; they require no synchronization.</li>
 * <li>Immutable objects are simple. Immutable objects can be shared freely.</li>
 * <li>Not only can you share immutable objects, but they can share their internals.</li>
 * <li>Immutable objects make great building blocks for other objects : One such case of this principle is that
 * immutable objects make great map keys and set elements: you don’t have to worry about their values changing once
 * they’re in the map or set, which would destroy the map or set’s invariants</li>
 * <li>Immutable objects provide failure atomicity for free. Their state never changes, so there is no possibility of a
 * temporary inconsistency.</li>
 * <li>The major disadvantage of immutable classes is that they require a separate object for each distinct value.
 * Creating these objects can be costly, especially if they are large. For example, suppose that you have a million-bit
 * {@link BigInteger} and you want to change its low-order bit.</li>
 * <li>The package-private mutable companion class approach works fine if you can accurately predict which complex
 * operations clients will want to perform on your immutable class. If not, then your best bet is to provide a public
 * mutable companion class. The main example of this approach in the Java platform libraries is the {@link String}
 * class, whose mutable companion is {@link StringBuilder} (and its obsolete predecessor, {@link StringBuffer}).</li>
 * <li>Classes should be immutable unless there’s a very good reason to make them mutable. Immutable classes provide
 * many advantages, and their only disadvantage is the potential for performance problems under certain circumstances.
 * You should always make small value objects.</li>
 * <li>If a class cannot be made immutable, limit its mutability as much as possible.</li>
 * <li>Constructors should create fully initialized objects with all of their invariants established.</li> <br>
 * 
 * @author Aritra
 *
 */
public final class ImmutableClass {

	/**
	 * Integer class is immutable as it does not provide any setter to change its content
	 */
	private final Integer immutableField1;

	/**
	 * String class is immutable as it also does not provide setter to change its content
	 */
	private final String immutableField2;

	/**
	 * Date class is mutable as it provide setters to change various date/time parts
	 */
	private final Date mutableField;

	/**
	 * Map probably is mutable unless Collections.unmodifiableMap() or something similar was used to instantiate it.
	 */
	private final Map<String, Object> map;

	// Default private constructor will ensure no unplanned construction of class
	private ImmutableClass(final Integer fld1, final String fld2, final Date date, final Map<String, Object> map) {
		this.immutableField1 = fld1;
		this.immutableField2 = fld2;
		this.mutableField = new Date(date.getTime());
		// this.map = map; // This is wrong as this does not provide Immutibility. Hence do a deep copy or clone.
		this.map = new HashMap<>();
		for (Map.Entry<String, Object> eachEntry : map.entrySet()) {
			this.map.put(eachEntry.getKey(), eachEntry.getValue());
		}
		// this.map = new HashMap<>(map); // Directly we can do this also instead.
	}

	// Factory method to store object creation logic in single place
	public static ImmutableClass createNewInstance(final Integer fld1, final String fld2, final Date date,
			final Map<String, Object> map) {
		return new ImmutableClass(fld1, fld2, date, map);
	}

	// Provide no setter methods

	/**
	 * Integer class is immutable so we can return the instance variable as it is
	 */
	public Integer getImmutableField1() {
		return immutableField1;
	}

	/**
	 * String class is also immutable so we can return the instance variable as it is
	 */
	public String getImmutableField2() {
		return immutableField2;
	}

	/**
	 * Date class is mutable so we need a little care here. We should not return the reference of original instance
	 * variable. Instead a new Date object, with content copied to it, should be returned.
	 */
	public Date getMutableField() {
		return new Date(mutableField.getTime());
	}

	public Map<String, Object> getMap() {
		return new HashMap<>(this.map);
	}

	@Override
	public String toString() {
		return immutableField1 + " - " + immutableField2 + " - " + mutableField + " - " + map;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this)
			return true;
		if (!(o instanceof ImmutableClass))
			return false;
		ImmutableClass c = (ImmutableClass) o;
		return compare(c);
	}

	private boolean compare(ImmutableClass c) {
		// Why compare is used instead of ==
		return Integer.compare(c.immutableField1, immutableField1) == 0
				&& immutableField2.equals(c.immutableField2)
				&& mutableField.equals(c.getMutableField()) && map.equals(c.getMap());
	}

	@Override
	public int hashCode() {
		return 31 * Integer.hashCode(immutableField1) + immutableField2.hashCode() + mutableField.hashCode()
				+ map.hashCode();
	}

}
