/**
 * @author Daniel gil
 */
package application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Classe que representa um Pagamento no sistema.
 * Esta entidade mapeia para a tabela "tb_payment" na base de dados.
 */
@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do pagamento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant moment; // Momento em que o pagamento foi realizado.

    /**
     * Relacionamento um-para-um com a entidade Order.
     * @JsonIgnore impede a serialização deste campo para evitar loops infinitos.
     * @MapsId indica que o ID desta entidade é o mesmo que o ID da entidade Order relacionada.
     */
    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order; // Associação com a entidade Order.

    /**
     * Construtor padrão da classe Payment.
     */
    public Payment() {
        // Construtor padrão necessário para JPA.
    }

    /**
     * Construtor da classe Payment.
     *
     * @param id     O ID do pagamento.
     * @param moment O momento em que o pagamento foi realizado.
     * @param order  O pedido associado ao pagamento.
     */
    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    /**
     * Obtém o ID do pagamento.
     *
     * @return O ID do pagamento.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do pagamento.
     *
     * @param id O ID do pagamento.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o momento em que o pagamento foi realizado.
     *
     * @return O momento do pagamento.
     */
    public Instant getMoment() {
        return moment;
    }

    /**
     * Define o momento em que o pagamento foi realizado.
     *
     * @param moment O momento do pagamento.
     */
    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    /**
     * Obtém o pedido associado ao pagamento.
     *
     * @return O pedido associado ao pagamento.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Define o pedido associado ao pagamento.
     *
     * @param order O pedido associado ao pagamento.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Verifica se dois objetos Payment são iguais.
     * A igualdade é baseada no ID do pagamento.
     *
     * @param o O objeto a comparar com este pagamento.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    /**
     * Calcula o código hash para este pagamento.
     * O código hash é baseado no ID do pagamento.
     *
     * @return O código hash do pagamento.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

