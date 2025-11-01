package School;


/**
 * Custom exception for errors related to Person objects.
 * @author stephan knappstein A01208242
 * @version 1.0
 */
public class IllegalPersonException extends RuntimeException {
    public IllegalPersonException(String message) {
        super(message);
    }
}
