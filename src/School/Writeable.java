package School;
/**
 * A functional interface for operations that involve processing a string
 * a number of times, determined by a min/max range.
 * @author stephan knappstein A01208242
 * @version 1.0
 */
@FunctionalInterface
public interface Writeable {
    /**
     * Processes the given string data within a specified numerical range.
     * @param s   The string to be processed.
     * @param min The inclusive minimum value of the range.
     * @param max The exclusive maximum value of the range.
     */
    void printData(String s, int min, int max);

}
