/**
 * @author Daniel Gil
 */
package application.resources.exeptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.Instant;

/**
 * Classe que representa um erro padrão para respostas de erro da API.
 * Esta classe é usada para formatar as informações de erro que são
 * retornadas ao cliente em caso de exceções.
 */
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp; // Momento em que o erro ocorreu.
    private Integer status;    // Código de status HTTP do erro.
    private String error;      // Breve descrição do tipo de erro.
    private String message;    // Mensagem detalhada sobre o erro.
    private String path;       // Caminho da requisição que causou o erro.

    /**
     * Construtor padrão da classe StandardError.
     */
    public StandardError() {
        // Construtor padrão.
    }

    /**
     * Construtor da classe StandardError.
     *
     * @param timestamp Momento em que o erro ocorreu.
     * @param status    Código de status HTTP do erro.
     * @param error     Breve descrição do tipo de erro.
     * @param message   Mensagem detalhada sobre o erro.
     * @param path      Caminho da requisição que causou o erro.
     */
    public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * Obtém o momento em que o erro ocorreu.
     *
     * @return O momento do erro.
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Define o momento em que o erro ocorreu.
     *
     * @param timestamp O momento do erro.
     */
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Obtém o código de status HTTP do erro.
     *
     * @return O código de status HTTP.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Define o código de status HTTP do erro.
     *
     * @param status O código de status HTTP.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Obtém a breve descrição do tipo de erro.
     *
     * @return A descrição do erro.
     */
    public String getError() {
        return error;
    }

    /**
     * Define a breve descrição do tipo de erro.
     *
     * @param error A descrição do erro.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Obtém a mensagem detalhada sobre o erro.
     *
     * @return A mensagem do erro.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Define a mensagem detalhada sobre o erro.
     *
     * @param message A mensagem do erro.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Obtém o caminho da requisição que causou o erro.
     *
     * @return O caminho da requisição.
     */
    public String getPath() {
        return path;
    }

    /**
     * Define o caminho da requisição que causou o erro.
     *
     * @param path O caminho da requisição.
     */
    public void setPath(String path) {
        this.path = path;
    }
}

