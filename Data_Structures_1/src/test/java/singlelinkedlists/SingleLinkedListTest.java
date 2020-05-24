package singlelinkedlists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class SingleLinkedListTest {

	@Test
	public void testSizeEmpty() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
		assertEquals("", list.toString());
	}

	@Test
	public void testListConstructorNull() {
		final List<Integer> parameterList = null;
		final SingleLinkedList<Integer> list = new SingleLinkedList<>(parameterList);
		assertTrue(list.isEmpty());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
		assertEquals("", list.toString());
	}

	@Test
	public void testListConstructorEmpty() {
		final List<Integer> parameterList = Collections.emptyList();
		final SingleLinkedList<Integer> list = new SingleLinkedList<>(parameterList);
		assertTrue(list.isEmpty());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
		assertEquals("", list.toString());
	}

	@Test
	public void testListConstructorSingleElementList() {
		final List<Integer> parameterList = List.of(1);
		final SingleLinkedList<Integer> list = new SingleLinkedList<>(parameterList);
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.isEmpty());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
		assertEquals("", list.toString());
	}

	@Test
	public void testListConstructorMultipleElementsList() {
		final List<Integer> parameterList = IntStream.range(0, 50).boxed().collect(Collectors.toList());
		final SingleLinkedList<Integer> list = new SingleLinkedList<>(parameterList);

		for (int i = 0; i < 50; i++) {
			assertEquals(i, list.get(i).intValue());
			assertEquals(parameterList.get(i), list.get(i));
		}

		assertFalse(list.isEmpty());
		assertEquals(50, list.size());

		assertTrue(list.contains(49));
		assertEquals(49, list.get(49).intValue());
		assertEquals(49, list.remove(49).intValue());
		assertFalse(list.contains(49));

		assertTrue(list.contains(10));
		assertEquals(10, list.get(10).intValue());
		assertEquals(10, list.remove(10).intValue());
		assertFalse(list.contains(10));

		assertTrue(list.contains(0));
		assertEquals(0, list.get(0).intValue());
		assertEquals(0, list.remove(0).intValue());
		assertFalse(list.contains(0));

		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test
	public void testClear() {
		final List<Integer> parameterList = IntStream.range(5, 10).boxed().collect(Collectors.toList());
		final SingleLinkedList<Integer> list = new SingleLinkedList<>(parameterList);
		System.out.println(list);

		for (int i = 0; i < 5; i++) {
			assertEquals(i + 5, list.get(i).intValue());
			assertEquals(parameterList.get(i), list.get(i));
		}

		assertFalse(list.isEmpty());
		assertEquals(5, list.size());
		assertNotEquals("", list.toString());

		list.clear();
		assertTrue(list.isEmpty());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
		assertEquals("", list.toString());
	}

	@Test
	public void testSizeNotEmpty() {
		final Node<Integer> node = new Node<>(9, null);

		SingleLinkedList<Integer> list = new SingleLinkedList<>(node);
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
		assertEquals("", list.toString());

		list = new SingleLinkedList<>(node);
		assertFalse(list.isEmpty());
		assertTrue(list.removeFromBeginning());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
		assertEquals("", list.toString());
	}

	@Test
	public void testSizeEqualToFiftyByAppending() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 50)
			list.add(i++);
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test
	public void testSizeEqualToFiftyByAddingAtIndex() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 50)
			list.add(i++);
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());

		list.add(10, 10);
		assertEquals(10, list.get(10).intValue());
		list.add(20, 25);
		assertEquals(25, list.get(20).intValue());
		list.add(45, 50);
		assertEquals(50, list.get(45).intValue());

		assertEquals(53, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test
	public void testSizeEqualToFiftyByAddingAtBeginning() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 50)
			list.addAtBeginning(i++);
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test
	public void testSizeEqualToFiftyAfterRemovalsFromEnd() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 100)
			list.add(i++);
		assertEquals(100, list.size());
		i--;

		while (i > 50) {
			assertFalse(list.isEmpty());
			assertEquals(i, list.size());
			list.remove();
			i--;
			assertEquals(i, list.size());
			assertFalse(list.isEmpty());
		}

		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test
	public void testSizeEqualToFiftyAfterRemovalsFromBeginning() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 100)
			list.add(i++);
		i--;
		while (i > 50) {
			assertFalse(list.isEmpty());
			assertEquals(i, list.size());
			list.removeFromBeginning();
			i--;
			assertEquals(i, list.size());
			assertFalse(list.isEmpty());
		}
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test
	public void testRemovalsOfAllElements() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 50)
			list.add(i++);

		System.out.println(list);
		while (list.remove())
			;

		assertEquals(0, list.size());

		assertTrue(list.isEmpty());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
		assertEquals("", list.toString());
	}

	@Test
	public void testRemovalsFromBeginningOfAllElements() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 50)
			list.add(i++);

		while (list.removeFromBeginning())
			;

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
		assertEquals("", list.toString());
	}

	@Test
	public void testRemovalsOfAllElementsSizeAtEachStep() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 50)
			list.add(i++);
		i--;
		while (i > 0) {
			assertFalse(list.isEmpty());
			assertEquals(i, list.size());
			list.remove();
			i--;
			assertEquals(i, list.size());
		}
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
		assertEquals("", list.toString());
	}

	@Test
	public void testGetMinSingle() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 50)
			list.add(i++);

		assertEquals(1, list.getMinNode(Comparator.naturalOrder()).val.intValue());
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test
	public void testGetMinDuplicates() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());

		int i = 1;
		while (i++ <= 50)
			list.add(10);

		assertEquals(10, list.getMinNode(Comparator.naturalOrder()).val.intValue());
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test
	public void testGetMaxSingle() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 50)
			list.add(i++);

		assertEquals(50, list.getMaxNode(Comparator.naturalOrder()).val.intValue());
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test
	public void testGetMaxDuplicates() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());
		int i = 1;
		while (i++ <= 50)
			list.add(10);

		assertEquals(10, list.getMaxNode(Comparator.naturalOrder()).val.intValue());
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test
	public void testMinOnEmptyList() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());
		assertNull(list.getMinNode(Comparator.naturalOrder()));
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 50)
			list.add(i++);

		assertEquals(1, list.getMinNode(Comparator.naturalOrder()).val.intValue());
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test
	public void testMaxOnEmptyList() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());
		assertNull(list.getMinNode(Comparator.naturalOrder()));
		assertTrue(list.isEmpty());

		int i = 1;
		while (i <= 50)
			list.add(i++);

		assertEquals(50, list.getMaxNode(Comparator.naturalOrder()).val.intValue());
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
		assertNotEquals("", list.toString());
	}

	@Test(expected = InvalidParameterException.class)
	public void testInvalidParameterExceptionForMinComparator() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		int i = 1;
		while (i++ <= 50)
			list.add(10);

		list.getMinNode(null).val.intValue();
	}

	@Test(expected = InvalidParameterException.class)
	public void testInvalidParameterExceptionForMaxComparator() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		int i = 1;
		while (i++ <= 50)
			list.add(10);

		list.getMaxNode(null).val.intValue();
	}

	@Test
	public void testReverseMultipleElementsInList() {
		final List<Integer> parameterList = IntStream.range(0, 10).boxed().collect(Collectors.toList());
		final SingleLinkedList<Integer> list = new SingleLinkedList<>(parameterList);

		list.reverse();
		Collections.reverse(parameterList);
		System.out.println(list);

		for (int i = 0; i < 10; i++) {
			assertEquals(9 - i, list.get(i).intValue());
			assertEquals(parameterList.get(i), list.get(i));
		}
	}

	@Test
	public void testReverseSingleElementInList() {
		final List<Integer> parameterList = List.of(10);
		final SingleLinkedList<Integer> list = new SingleLinkedList<>(parameterList);

		list.reverse();
		Collections.reverse(parameterList);

		assertEquals(10, list.get(0).intValue());
		assertEquals(parameterList.get(0), list.get(0));
	}

	@Test
	public void testReverseEmptyList() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());
		assertEquals("", list.toString());
		list.reverse();
		assertTrue(list.isEmpty());
		assertEquals("", list.toString());
	}

}
