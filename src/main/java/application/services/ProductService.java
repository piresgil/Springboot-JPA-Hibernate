/**
 * @author Daniel Gil
 */
package application.services;

import application.entities.Product;
import application.repositories.ProductRepository;
import application.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Class Order Service
 * Esta classe representa a camada de serviço para a entidade Product (Produto).
 * Ela contém a lógica de negócios para realizar operações relacionadas à entidade Product.
 * Neste caso, as operações estão limitadas a leitura (Read).
 *
 * @Service indica que esta classe é um componente de serviço do Spring.
 */
@Service
public class ProductService {

    // Injeção de Dependências
    // @Autowired injeta uma instância de ProductRepository. Isso permite que a classe de serviço
    // interaja com o banco de dados através do repositório.
    @Autowired
    private ProductRepository repository;

    /**
     * Busca todos os produtos.
     * @return Uma lista contendo todos os produtos cadastrados.
     */
    public List<Product> findAll() {
        return repository.findAll();
    }

    /**
     * Busca um produto pelo seu ID.
     * Optional<Product> é uma classe introduzida no Java 8
     * com o objetivo de fornecer uma forma mais segura
     * e expressiva de lidar com valores que podem ser nulos.
     * Ela encapsula um valor opcional, indicando se um valor está presente ou não.
     * Isso ajuda a evitar o famigerado NullPointerException, um dos erros mais comuns em Java.
     *
     * @param id O ID do produto a ser buscado.
     * @return O produto encontrado.
     */
    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
        // obj.orElseThrow(() -> new ResourceNotFoundException(id)); //Forma mais concisa de retornar o objeto ou lançar a exceção
    }
}
