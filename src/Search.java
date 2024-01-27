package src;

public class Search {
    public record Solution(int index, long depth) {
    }

    public static final Integer FALLBACK_VALUE = Integer.MIN_VALUE;

    public static Solution search(Integer[] array, Integer element, int n) {
        return search(array, element, 0, array.length - 1, n, 1);
    }

    private static Solution search(Integer[] array,
                                   Integer element,
                                   int low,
                                   int high,
                                   int n,
                                   int depth) {
        if (high < low ||
                high >= array.length ||
                low < 0L) {
            return new Solution(FALLBACK_VALUE, depth);
        }

        int numberOfElements = high - low + 1;
        if (numberOfElements <= n) {
            for (int i = low; i <= high; i++) {
                if (array[i].equals(element)) {
                    return new Solution(i, depth);
                }
            }
            return new Solution(FALLBACK_VALUE, depth);
        } else {
            int div = (int) Math.ceil(((high - low) / (double) n));
            int r = low + div;
            for (; r <= high; r += div) {
                int found = array[r].compareTo(element);
                if (found > 0) {
                    break;
                } else if (found == 0) {
                    return new Solution(r, depth);
                }
            }
            int l = Math.max(low, r - div);
            r = Math.min(high, r);
            return search(array, element, l, r, n, depth + 1);
        }
    }
}