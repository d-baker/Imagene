package imagene.watchmaker;

/**
 * Created by dbaker on 3/11/16.
 */
public class UnexpectedParentsException extends Exception {
    public UnexpectedParentsException() {
        super("Unexpected number of parents");
    }

    public UnexpectedParentsException(String message) {
        super(message);
    }
}
