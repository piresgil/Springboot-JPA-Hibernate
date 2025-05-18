/**
 * @author Daniel Gil
 */
package application.services.exeptions;

/**
 * Exceção para indicar que um recurso não foi encontrado.
 * <p>
 * Esta exceção é usada para sinalizar que uma tentativa de buscar
 * um recurso (por exemplo, por ID) falhou porque o recurso não existe.
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe ResourceNotFoundException.
     *
     * @param id O ID do recurso que não foi encontrado.
     * A mensagem da exceção inclui o ID para fornecer
     * informações úteis sobre qual recurso estava sendo procurado.
     */
    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id " + id);
    }
}