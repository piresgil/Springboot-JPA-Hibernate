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
 * Esta classe representa a camada de serviço para a entidade User (Usuário).
 * Ela contém a lógica de negócios para realizar operações CRUD (Create, Read, Update, Delete)
 * e outras operações relacionadas à entidade User.
 *
 * @Service indica que esta classe é um componente de serviço do Spring.
 */
@Service
public class UserService {

    // Injeção de Dependências
    // @Autowired injeta uma instância de UserRepository. Isso permite que a classe de serviço
    // interaja com o banco de dados através do repositório.
    @Autowired
    private UserRepository repository;

    /**
     * Busca todos os usuários.
     * @return Uma lista contendo todos os usuários cadastrados.
     */
    public List<User> findAll() {
        return repository.findAll();
    }

    /**
     * Busca um usuário pelo seu ID.
     * Optional<User> é uma classe introduzida no Java 8
     * com o objetivo de fornecer uma forma mais segura
     * e expressiva de lidar com valores que podem ser nulos.
     * Ela encapsula um valor opcional, indicando se um valor está presente ou não.
     * Isso ajuda a evitar o famigerado NullPointerException, um dos erros mais comuns em Java.
     *
     * @param id O ID do usuário a ser buscado.
     * @return O usuário encontrado, ou lança uma exceção ResourceNotFoundException se não encontrado.
     */
    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
        // obj.orElseThrow(() -> new ResourceNotFoundException(id)); //Forma mais concisa de retornar o objeto ou lançar a exceção
    }

    /**
     * Insere um novo usuário.
     * @param obj O objeto User contendo os dados do usuário a ser inserido.
     * @return O usuário inserido, com o ID gerado pelo banco de dados.
     */
    public User insert(User obj) {
        return repository.save(obj);
    }

    /**
     * Deleta um usuário pelo seu ID.
     * Esta versão do método delete inclui tratamento de exceções para ResourceNotFoundException
     * e DataIntegrityViolationException.
     * @param id O ID do usuário a ser deletado.
     * @throws ResourceNotFoundException Se o ID não existir.
     * @throws DatabaseException Se ocorrer um erro de integridade de dados (ex: violação de chave estrangeira).
     */
    public void delete(Long id) {
        // NOVA ATUALIZAÇÃO do Spring boot, necessita de uma validação para dar o erro 404
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException(id);
            }
            repository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(id); // Propaga a exceção ResourceNotFoundException
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage()); // Lança DatabaseException para erros de integridade
        }
    }

    /**
     * Atualiza um usuário existente.
     * Este método busca o usuário no banco de dados, atualiza os campos permitidos
     * e salva as alterações.
     * @param id O ID do usuário a ser atualizado.
     * @param obj O objeto User contendo os novos dados do usuário.
     * @return O usuário atualizado.
     * @throws ResourceNotFoundException Se o ID não existir.
     */
    public User update(Long id, User obj) {
        try {
            // Usa getReferenceById para obter uma referência para a entidade sem buscar os dados do banco de dados
            // Se o ID não existir, EntityNotFoundException será lançada ao tentar acessar os dados
            User entity = repository.getReferenceById(id);
            updateData(entity, obj); // Método auxiliar para atualizar os dados da entidade
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id); // Lança ResourceNotFoundException se o ID não existir
        }
    }

    /**
     * Método auxiliar para atualizar os dados de um usuário existente com os dados de um novo objeto User.
     * Este método atualiza apenas os campos que podem ser atualizados (name, email, phone).
     * @param entity A entidade User a ser atualizada (obtida do banco de dados).
     * @param obj O objeto User contendo os novos dados.
     */
    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
        // A senha NÃO é atualizada aqui, por questões de segurança.  A atualização da senha
        // deve ser feita em um método separado com lógica de validação e criptografia adequadas.
    }
}
