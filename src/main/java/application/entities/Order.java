/**
 * @author Daniel gil
 */
package application.entities;

import application.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe que representa um Pedido no sistema.
 * Esta entidade mapeia para a tabela "tb_order" na base de dados.
 */
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único do pedido.

    private Instant moment; // Momento em que o pedido foi realizado.

    private Integer orderStatus; // Status do pedido (ver enum OrderStatus).

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client; // Cliente que realizou o pedido.

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>(); // Itens do pedido.

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment; // Pagamento associado ao pedido.

    /**
     * Construtor padrão da classe Order.
     */
    public Order() {
        // Construtor padrão necessário para o JPA.
    }

    /**
     * Construtor da classe Order.
     *
     * @param id          Identificador do pedido.
     * @param moment      Momento em que o pedido foi realizado.
     * @param orderStatus Status do pedido.
     * @param client      Cliente que realizou o pedido.
     */
    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        super();
        this.id = id;
        this.moment = moment;
        this.client = client;
        setOrderStatus(orderStatus); // Garante que o status seja atribuído corretamente.
    }

    /**
     * Obtém o identificador do pedido.
     *
     * @return O identificador do pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador do pedido.
     *
     * @param id O identificador do pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o momento em que o pedido foi realizado.
     *
     * @return O momento do pedido.
     */
    public Instant getMoment() {
        return moment;
    }

    /**
     * Define o momento em que o pedido foi realizado.
     *
     * @param moment O momento do pedido.
     */
    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    /**
     * Obtém o cliente que realizou o pedido.
     *
     * @return O cliente do pedido.
     */
    public User getClient() {
        return client;
    }

    /**
     * Define o cliente que realizou o pedido.
     *
     * @param client O cliente do pedido.
     */
    public void setClient(User client) {
        this.client = client;
    }

    /**
     * Obtém o status do pedido.
     *
     * @return O status do pedido.
     */
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    /**
     * Define o status do pedido.
     *
     * @param orderStatus O status do pedido.
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    /**
     * Obtém o pagamento associado ao pedido.
     *
     * @return O pagamento do pedido.
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Define o pagamento associado ao pedido.
     *
     * @param payment O pagamento do pedido.
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     * Obtém os itens do pedido.
     *
     * @return Os itens do pedido.
     */
    public Set<OrderItem> getItems() {
        return items;
    }

    /**
     * Calcula o valor total do pedido.
     *
     * @return O valor total do pedido.
     */
    public Double getTotal() {
        double sum = 0.0;
        for (OrderItem x : items) {
            sum += x.getSubTotal(); // Calcula o subtotal de cada item e soma.
        }
        return sum;
    }

    /**
     * Calcula o código hash do objeto Order.
     *
     * @return O código hash do objeto.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Verifica se dois objetos Order são iguais.
     *
     * @param obj O objeto a comparar com este Order.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
