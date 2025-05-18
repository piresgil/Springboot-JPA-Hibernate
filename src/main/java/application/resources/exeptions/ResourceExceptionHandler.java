/**
 * @author Daniel Gil
 */
package application.resources.exeptions;

import application.services.exeptions.DatabaseException;
import application.services.exeptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;

/**
 * Classe que trata exceções de recursos não encontrados e erros de banco de dados.
 * Esta classe centraliza o tratamento de exceções para que não seja necessário
 * tratar cada exceção individualmente em cada controlador.
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    /**
     * Método que trata a exceção ResourceNotFoundException.
     * Este método é chamado quando uma ResourceNotFoundException é lançada
     * em qualquer controlador da aplicação.
     *
     * @param e       A exceção ResourceNotFoundException que foi lançada.
     * @param request O objeto HttpServletRequest que representa a requisição HTTP que causou a exceção.
     * @return Um ResponseEntity contendo um objeto StandardError com detalhes sobre a exceção.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND; // 404

        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Método que trata a exceção DatabaseException.
     * Este método é chamado quando uma DatabaseException é lançada
     * em qualquer controlador da aplicação.
     *
     * @param e       A exceção DatabaseException que foi lançada.
     * @param request O objeto HttpServletRequest que representa a requisição HTTP que causou a exceção.
     * @return Um ResponseEntity contendo um objeto StandardError com detalhes sobre a exceção.
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400

        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
