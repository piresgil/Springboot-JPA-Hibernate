/**
 * @author Daniel Gil
 */
package application.services.exeptions;

/**
 * Exceção Data Base Exception
 */
public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg) {
        super(msg);
    }
}
