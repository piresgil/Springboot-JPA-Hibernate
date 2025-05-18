/**
 * @author Daniel Gil
 */
package application.services;

import application.entities.Order;
import application.repositories.OrderRepository;
import application.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class Order Service
 * Esta classe representa a camada de serviço para a entidade Order (Pedido).
 * Ela contém a lógica de negócios para realizar operações relacionadas à entidade Order.
 * Neste caso, as operações estão limitadas a leitura (Read).
 *
 * @Service indica que esta classe é um componente de serviço do Spring.
 */
@Service
public class OrderService {

    // Injeção de Dependências
    // @Autowired injeta uma instância de OrderRepository. Isso permite que a classe de serviço
    // interaja com o banco de dados através do repositório.
    @Autowired
    private OrderRepository repository;

    /**
     * Busca todos os pedidos.
     *
     * @return Uma lista contendo todos os pedidos cadastrados.
     */
    public List<Order> findAll() {
        return repository.findAll();
    }

    /**
     * Busca um pedido pelo seu ID.
     * Optional<Order> é uma classe introduzida no Java 8
     * com o objetivo de fornecer uma forma mais segura
     * e expressiva de lidar com valores que podem ser nulos.
     * Ela encapsula um valor opcional, indicando se um valor está presente ou não.
     * Isso ajuda a evitar o famigerado NullPointerException, um dos erros mais comuns em Java.
     *
     * @param id O ID do pedido a ser buscado.
     * @return O pedido encontrado, ou lança uma exceção ResourceNotFoundException se não encontrado.
     */
    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
        // obj.orElseThrow(() -> new ResourceNotFoundException(id)); //Forma mais concisa de retornar o objeto ou lançar a exceção
    }
}
