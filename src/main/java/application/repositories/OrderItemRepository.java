/**
 * @author Daniel Gil
 */
package application.repositories;

import application.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface User repository
 * Esta interface estende JpaRepository, fornecendo uma abstração para acesso a dados.
 * JpaRepository já implementa a interface Repository, ou seja,
 * ela serve como uma camada intermediária entre a lógica de negócio da sua aplicação e o banco de dados.
 * JpaRepository já possui a anotação @Repository, então não é necessário colocar aqui.
 */
// @Repository // Não é necessário, pois JpaRepository já é anotado com @Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // Esta interface herda métodos do JpaRepository, como:
    // - save(OrderItem entity):        Salva ou atualiza um item de pedido.
    // - findById(Long id):      Busca um item de pedido por ID, retornando um Optional<OrderItem>.
    // - findAll():             Busca todos os itens de pedido.
    // - deleteById(Long id):    Deleta um item de pedido por ID.
    // - delete(OrderItem entity):    Deleta um item de pedido.
    //
    // Não é necessário implementar esses métodos aqui; o Spring Data JPA os fornece automaticamente.

    // Se você precisar de métodos de consulta personalizados que não estão incluídos no JpaRepository,
    // você pode declará-los aqui.  No entanto, OrderItem geralmente é gerenciado através de Order,
    // então consultas personalizadas podem ser menos comuns.  Exemplo:
    //
    //  List<OrderItem> findByOrder(Order order);
    //
    // O Spring Data JPA irá gerar a implementação desses métodos para você.
}
