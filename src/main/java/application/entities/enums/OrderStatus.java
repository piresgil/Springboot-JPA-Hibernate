/**
 * @author Daniel Gil
 */
package application.entities.enums;

/**
 * Enum Order Status
 * Este enum representa os possíveis status de um pedido.  Enums são usados para definir
 * um conjunto fixo de valores possíveis para uma variável.  Neste caso, um pedido pode ter
 * um dos seguintes status: WAITING_PAYMENT, PAID, SHIPPED, DELIVERED, CANCELED.
 */
public enum OrderStatus {

    // Cada constante do enum representa um status de pedido possível, e está associada a um código inteiro.
    WAITING_PAYMENT(1), // Aguardando Pagamento (código 1)
    PAID(2),           // Pago (código 2)
    SHIPPED(3),        // Enviado (código 3)
    DELIVERED(4),      // Entregue (código 4)
    CANCELED(5);       // Cancelado (código 5)

    private int code; // Cada status tem um código inteiro associado.

    // Construtor do enum.  Construtores de enum são sempre privados.
    private OrderStatus(int code) {
        this.code = code;
    }

    // Getter para obter o código do status.
    public int getCode() {
        return code;
    }

    /**
     * Método estático para obter o OrderStatus a partir de um código inteiro.
     * Este método é importante para converter um código do banco de dados (ou de uma requisição)
     * para o valor do enum correspondente.
     *
     * @param code O código do status do pedido.
     * @return O OrderStatus correspondente ao código.
     * @throws IllegalArgumentException Se o código for inválido (não corresponder a nenhum status).
     */
    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) { // Itera sobre todos os valores do enum.
            if (value.getCode() == code) { // Se o código do valor do enum corresponder ao código fornecido,
                return value;             // retorna o valor do enum.
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code: " + code); // Se o código não corresponder a nenhum status, lança uma exceção.
    }
}

