/**
 * 
 */
package immutable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author Aritra
 *
 */
public class ImmutableClassTest {

	private static final Integer fld1 = 9;

	private static final String fld2 = "TEST";

	private static final Date date = new Date(System.currentTimeMillis());

	private static final Map<String, Object> map = new HashMap<>();

	/**
	 * Test method for {@link immutable.ImmutableClass#createNewInstance(java.lang.Integer, java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testCreateNewInstance() {
		populateMap();
		ImmutableClass immutable = ImmutableClass.createNewInstance(fld1, fld2, date, map);
		map.put("RANDOM1", "RANDOM1");
		map.put("RANDOM2", "RANDOM2");
		map.put("RANDOM3", "RANDOM3");
		assertEquals(immutable, immutable);
		assertNotEquals(immutable, "");
		assertEquals(immutable.hashCode(), immutable.hashCode());
		assertTrue(immutable.equals(immutable));
	}

	/**
	 * Test method for {@link immutable.ImmutableClass#getImmutableField1()}.
	 */
	@Test
	public void testGetImmutableField1() {
		populateMap();
		ImmutableClass immutable = ImmutableClass.createNewInstance(fld1, fld2, date, map);
		assertEquals(fld1, immutable.getImmutableField1());
	}

	/**
	 * Test method for {@link immutable.ImmutableClass#getImmutableField2()}.
	 */
	@Test
	public void testGetImmutableField2() {
		populateMap();
		ImmutableClass immutable = ImmutableClass.createNewInstance(fld1, fld2, date, map);
		assertEquals(fld2, immutable.getImmutableField2());
	}

	/**
	 * Test method for {@link immutable.ImmutableClass#getMutableField()}.
	 */
	@Test
	public void testGetMutableField() {
		populateMap();
		ImmutableClass immutable = ImmutableClass.createNewInstance(fld1, fld2, date, map);
		assertEquals(date, immutable.getMutableField());
	}

	@Test
	public void testForModificationOfImmutableField() {
		populateMap();
		ImmutableClass immutable = ImmutableClass.createNewInstance(fld1, fld2, date, map);
		System.out.println("Before trying for modification : " + immutable);
		tryModification(immutable.getImmutableField1(), immutable.getImmutableField2(), immutable.getMutableField(),
				immutable.getMap());
		System.out.println("After trying for modification : " + immutable);
		assertEquals(fld1, immutable.getImmutableField1());
		assertEquals(fld2, immutable.getImmutableField2());
		assertEquals(date, immutable.getMutableField());
	}

	@Test
	public void testTwoImmutablesWithSameParametersExceptMap() {
		populateMap();
		ImmutableClass immutable1 = ImmutableClass.createNewInstance(fld1, fld2, date, map);
		// System.out.println("Before : " + map.hashCode());
		map.put("RANDOM1", "RANDOM1");
		map.put("RANDOM2", "RANDOM2");
		map.put("RANDOM3", "RANDOM3");
		// System.out.println("After : " + map.hashCode());
		ImmutableClass immutable2 = ImmutableClass.createNewInstance(fld1, fld2, date, map);
		// System.out.println(immutable1 + "\n" + immutable2);
		assertNotEquals(immutable1, immutable2);
		// System.out.println(immutable1.hashCode() + " " + immutable2.hashCode());
		// https://stackoverflow.com/questions/6493605/how-does-a-java-hashmap-handle-different-objects-with-the-same-hash-code
		// https://stackoverflow.com/questions/16400711/two-unequal-objects-with-same-hashcode
		// assertNotEquals(immutable1.hashCode(), immutable2.hashCode());
		assertFalse(immutable1.equals(immutable2));
	}

	@Test
	public void testTwoImmutablesWithSameParametersExceptImmutableOne() {
		populateMap();
		ImmutableClass immutable1 = ImmutableClass.createNewInstance(fld1, fld2, date, map);
		ImmutableClass immutable2 = ImmutableClass.createNewInstance(fld1, fld2 + "_RANDOM", date, map);
		assertNotEquals(immutable1, immutable2);
		// System.out.println(immutable1.hashCode() + " " + immutable2.hashCode());
		assertNotEquals(immutable1.hashCode(), immutable2.hashCode());
		assertFalse(immutable1.equals(immutable2));
	}
	private static void tryModification(Integer immutableField1, String immutableField2, Date mutableField,
			final Map<String, Object> map) {
        immutableField1 = 10000;
		immutableField2 = "Test changed";
		mutableField.setTime(System.currentTimeMillis());
		map.put("MUTATED_STRING", -1);
    }

	private void populateMap() {
		map.put("SOME_INT", 1);
		map.put("SOME_DOUBLET", 1.0d);
		map.put("SOME_STRING", "TEST_STRING");
		List<Boolean> boolList = new ArrayList<>();
		boolList.add(true);
		boolList.add(false);
		boolList.add(true);
		map.put("SOME_BOOLEAN_LIST", boolList);
	}

}
