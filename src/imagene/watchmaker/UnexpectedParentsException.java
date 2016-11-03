package imagene.watchmaker;

/*****************************************
 * Written by Dorothea Baker (s3367422)
 * for
 * Programming Project 1
 * SP3 2016
 ****************************************/


public class UnexpectedParentsException extends Exception {
    public UnexpectedParentsException() {
        super("Unexpected number of parents");
    }

    public UnexpectedParentsException(String message) {
        super(message);
    }
}
