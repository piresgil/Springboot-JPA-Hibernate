/**
 * @author Daniel Gil
 */
package application.services;

import application.entities.Product;
import application.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class Order Service
 */

@Service
public class ProductService {

    // Injeção de Dependências
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    /**
     * Optional<T> é uma classe introduzida no Java 8
     * com o objetivo de fornecer uma forma mais segura
     * e expressiva de lidar com valores que podem ser nulos.
     * Ela encapsula um valor opcional, indicando se um valor está presente ou não.
     * Isso ajuda a evitar o famigerado NullPointerException, um dos erros mais comuns em Java.
     *
     * @param id
     * @return
     */
    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }
}
