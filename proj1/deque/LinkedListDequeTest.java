package deque;

import org.junit.Test;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkListDeque<String> lld1 = new LinkListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {


        LinkListDeque<Integer> lld1 = new LinkListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {



        LinkListDeque<Integer> lld1 = new LinkListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        LinkListDeque<String>  lld1 = new LinkListDeque<String>();
        LinkListDeque<Double>  lld2 = new LinkListDeque<Double>();
        LinkListDeque<Boolean> lld3 = new LinkListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {


        LinkListDeque<Integer> lld1 = new LinkListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {


        LinkListDeque<Integer> lld1 = new LinkListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }
    @Test
    public void constructorAddAndRemoveTest() {
        LinkListDeque<Integer> test = new LinkListDeque<>();
        test.addFirst(62);
        test.addFirst(34);
        test.addLast(56);
        test.removeFirst();
        test.removeLast();

    }

    @Test
    public void printTest() {
        LinkListDeque<String> test= new LinkListDeque<>();
        test.addLast("apples");
        test.addFirst("I");
        test.printDeque();
        LinkListDeque<Integer> test2 = new LinkListDeque<>();
        test2.printDeque();
        LinkListDeque<Double> test3 = new LinkListDeque<>();
        test.printDeque();
    }

    @Test

    public void equalsTest() {
        LinkListDeque<String> test= new LinkListDeque<>();
        test.addLast("apples");
        test.addFirst("I");

        LinkListDeque<String> test2 = new LinkListDeque<>();
        test2.addLast("apples");
        test2.addFirst("I");
        assertTrue(test.equals(test2));
        assertFalse(test.equals("null"));
        assertFalse(test2.equals("fiueh"));



    }

    @Test
    public void getTest() {
        LinkListDeque<String> test=new LinkListDeque<>();
        test.addLast("apple");
        test.addFirst("I love");

        assertEquals("apple",test.get(1));
        assertEquals("I love",test.get(0));
    }

    @Test
    public void getRecursiveTest() {
        LinkListDeque<String> test=new LinkListDeque<>();
        test.addLast("apple");
        test.addFirst("I love");

        assertEquals("apple",test.getRecursive(1));
        assertEquals("I love",test.getRecursive(0));
    }
}
