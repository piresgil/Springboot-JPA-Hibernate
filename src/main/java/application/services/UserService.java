/**
 * @author Daniel Gil
 */
package application.services;

import application.entities.User;
import application.repositories.UserRepository;
import application.services.exeptions.DatabaseException;
import application.services.exeptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class User Service
 */

@Service
public class UserService {


    // Injeção de Dependências
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
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
    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        // NOVA ATUALIZAÇÃO do Spring boot, necessita de uma validação para dar o erro 404
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException(id);
            }
            repository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj) {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
