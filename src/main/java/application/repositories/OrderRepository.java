/**
 * @author Daniel Gil
 */
package application.repositories;

import application.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Order repository
 * Esta interface estende JpaRepository, fornecendo uma abstração para acesso a dados.
 * JpaRepository já implementa a interface Repository, ou seja,
 * ela serve como uma camada intermediária entre a lógica de negócio da sua aplicação e o banco de dados.
 * JpaRepository já possui a anotação @Repository, então não é necessário colocar aqui.
 *
 * JpaRepository<Order, Long>:
 * - Order: O tipo da entidade que este repositório gerencia (neste caso, Order).
 * - Long:  O tipo do ID da entidade (neste caso, Long, que corresponde ao tipo do campo @Id em Order).
 */
// @Repository // Não é necessário, pois JpaRepository já é anotado com @Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Esta interface herda métodos do JpaRepository, como:
    // - save(Order entity):        Salva ou atualiza um pedido.
    // - findById(Long id):      Busca um pedido por ID, retornando um Optional<Order>.
    // - findAll():             Busca todos os pedidos.
    // - deleteById(Long id):    Deleta um pedido por ID.
    // - delete(Order entity):    Deleta um pedido.
    //
    // Não é necessário implementar esses métodos aqui; o Spring Data JPA os fornece automaticamente.

    // Se você precisar de métodos de consulta personalizados que não estão incluídos no JpaRepository,
    // você pode declará-los aqui.  Por exemplo:
    //
    //  List<Order> findByClient(User client);
    //  List<Order> findByMomentBetween(Instant start, Instant end);
    //
    // O Spring Data JPA irá gerar a implementação desses métodos para você.
}
