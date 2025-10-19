package School;
/**
 * Represents an object that is part of an ordered sequence,
 * allowing traversal to the next and previous elements.
 * @author stephan knappstein A01208242
 * @version 1.0
 */
public interface Orderable {
    /**
     * @return the next Orderable object in the sequence.
     */
    public Orderable next();
    /**
     * @return the previous Orderable object in the sequence.
     */
    public Orderable previous();
}
