/**
 * @author Daniel Gil
 */
package application.services;

import application.entities.Category;
import application.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class Order Service
 */

@Service
public class CategoryService {

    // Injeção de Dependências
    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
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
    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }
}
