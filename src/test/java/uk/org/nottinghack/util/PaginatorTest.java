package uk.org.nottinghack.util;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class PaginatorTest
{
    private static final int MODULUS = 8;

    @Test
    public void onPage1of18_shouldReturnPageNumbers1to9()
    {
        assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, Paginator.paginate(1, 18, MODULUS));
    }

    @Test
    public void onPage18of18_shouldReturnPageNumbers10to18()
    {
        assertArrayEquals(new int[] {10, 11, 12, 13, 14, 15, 16, 17, 18}, Paginator.paginate(18, 18, MODULUS));
    }

    @Test
    public void onPage9of18_shouldReturnPageNumbers5to13()
    {
        assertArrayEquals(new int[] {5, 6, 7, 8, 9, 10, 11, 12, 13}, Paginator.paginate(9, 18, MODULUS));
    }

    @Test
    public void onPage1of1_shouldReturnPageNumber1()
    {
        assertArrayEquals(new int[] {1}, Paginator.paginate(1, 1, MODULUS));
    }

    @Test
    public void onPage1of4_shouldReturnPageNumbers1to4()
    {
        assertArrayEquals(new int[] {1, 2, 3, 4}, Paginator.paginate(1, 4, MODULUS));
    }
}
