package singlelinkedlists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SingleLinkedListTest {

	@Test
	public void testSizeEmpty() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		assertTrue(list.isEmpty());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
	}

	@Test
	public void testSizeNotEmpty() {
		final Node<Integer> node = new Node<>(9, null);

		SingleLinkedList<Integer> list = new SingleLinkedList<>(node);
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());

		list = new SingleLinkedList<>(node);
		assertFalse(list.isEmpty());
		assertTrue(list.removeFromBeginning());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
	}

	@Test
	public void testSizeEqualToFiftyByAppending() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		int i = 1;
		while (i <= 50)
			list.add(i++);
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
	}

	@Test
	public void testSizeEqualToFiftyByAddingAtBeginning() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		int i = 1;
		while (i <= 50)
			list.addAtBeginning(i++);
		assertEquals(50, list.size());
		assertFalse(list.isEmpty());
		assertTrue(list.remove());
		assertTrue(list.removeFromBeginning());
	}

	@Test
	public void testSizeEqualToFiftyAfterRemovalsFromEnd() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
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
	}

	@Test
	public void testSizeEqualToFiftyAfterRemovalsFromBeginning() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
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
	}

	@Test
	public void testRemovalsOfAllElements() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		int i = 1;
		while (i <= 500)
			list.add(i++);

		while (list.remove());

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
	}


	@Test
	public void testRemovalsFromBeginningOfAllElements() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
		int i = 1;
		while (i <= 50)
			list.add(i++);

		while (list.removeFromBeginning());

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.remove());
		assertFalse(list.removeFromBeginning());
	}

	@Test
	public void testRemovalsOfAllElementsSizeAtEachStep() {
		final SingleLinkedList<Integer> list = new SingleLinkedList<>();
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
	}

}
