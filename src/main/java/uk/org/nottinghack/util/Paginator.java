package uk.org.nottinghack.util;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class Paginator
{
    /**
     * Based on pagination implementation from CakePHP 2.6.2 Cake/View/Helper/PaginatorHelper.php
     * @param page the current page number, must be between 1 and <code>pageCount</code>.
     * @param pageCount the total number of pages.
     * @param modulus how many numbers to include on either side of the current page.
     * @return array of page numbers.
     */
    public static int[] paginate(int page, int pageCount, int modulus)
    {
        if (pageCount > 0 && (page < 1 || page > pageCount))
        {
            throw new IllegalArgumentException("page must be between 1 and " + pageCount + " but was " + page);
        }

        int[] pageNumbers;
        int arrayIndex = 0;

        if (pageCount > modulus)
        {
            pageNumbers = new int[modulus + 1];

            int half = modulus / 2;
            int end = page + half;

            if (end > pageCount)
            {
                end = pageCount;
            }

            int start = page - (modulus - (end - page));
            if (start <= 1)
            {
                start = 1;
                end = page + (modulus - page) + 1;
            }

            int i;
            for (i = start; i < page; i++)
            {
                pageNumbers[arrayIndex++] = i;
            }

            pageNumbers[arrayIndex++] = page;
            start = page + 1;

            for (i = start; i < end; i++)
            {
                pageNumbers[arrayIndex++] = i;
            }

            if (end != page)
            {
                pageNumbers[arrayIndex++] = i;
            }
        }
        else
        {
            pageNumbers = new int[pageCount];
            for (int i = 1; i <= pageCount; i++)
            {
                pageNumbers[arrayIndex++] = i;
            }
        }

        return pageNumbers;
    }
}
