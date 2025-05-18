/**
 * @author Daniel Gil
 */
package application.services.exeptions;

/**
 * Exceção para erros relacionados ao banco de dados.
 * Esta exceção é usada para encapsular e sinalizar problemas
 * que ocorrem durante a interação com o banco de dados,
 * como violações de integridade, falhas de conexão, etc.
 */
public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe DatabaseException.
     *
     * @param msg A mensagem detalhada sobre o erro do banco de dados.
     * Esta mensagem deve fornecer informações específicas sobre a causa do problema.
     */
    public DatabaseException(String msg) {
        super(msg);
    }
}