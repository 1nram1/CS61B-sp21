package deque;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void maxTest() {
        IntegerComparator comparator1 =new IntegerComparator();
        MaxArrayDeque<Integer> test = new MaxArrayDeque<>(comparator1);
        test.addFirst(7);
        test.addFirst(4);
        test.addLast(5);
        test.addLast(8);
        //4758
        int expected =8;
        int result = test.max();
        assertEquals(expected,result);
    }
}
