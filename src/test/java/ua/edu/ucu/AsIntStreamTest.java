package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author andrii
 */
public class AsIntStreamTest {

    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testStreamAverage() {
        System.out.println("average");
        double expResult = 1.0;
        double result = intStream.average();
        assertEquals(expResult, (Object) result);
    }

    @Test
    public void testStreamMax() {
        System.out.println("max");
        double expResult = 3;
        double result = intStream.max();
        assertEquals(expResult, (Object) result);
    }

    @Test
    public void testStreamMin() {
        System.out.println("min");
        double expResult = -1;
        double result = intStream.min();
        assertEquals(expResult, (Object) result);
    }

    @Test
    public void testStreamCount() {
        System.out.println("count");
        double expResult = 5;
        double result = intStream.count();
        assertEquals(expResult, (Object) result);
    }

    @Test
    public void testStreamSum() {
        System.out.println("sum");
        double expResult = 5;
        double result = intStream.sum();
        assertEquals(expResult, (Object) result);
    }

    @Test
    public void testStreamFilter() {
        System.out.println("filter");
        IntStream expResult = AsIntStream.of(1, 2, 3);
        IntStream pr_res = AsIntStream.of(-1, 0, 1, 2, 3);
        IntStream result = pr_res.filter(x -> x > 0);
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    @Test
    public void testStreamMap() {
        System.out.println("map");
        IntStream expResult = AsIntStream.of(3, 6, 9);
        IntStream pr_res = AsIntStream.of(1, 2, 3);
        IntStream result = pr_res.map(x -> x * 3);
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    @Test
    public void testStreamFlatMap() {
        System.out.println("flatMap");
        IntStream expResult = AsIntStream.of(-2, 1, -1, 2, 2, 5, 5, 8);
        IntStream pr_res = AsIntStream.of(1, 2, 5, 8);
        IntStream result = pr_res.flatMap(x -> AsIntStream.of(x - 3, x));
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    @Test
    public void testStreamReduce() {
        System.out.println("reduce");
        int expResult = 31;
        IntStream intStream = AsIntStream.of(1, 4, 6, 9, 11);
        int result = intStream.reduce(0, (sum, x) -> sum += x);
        assertEquals(expResult, result);
    }
}
