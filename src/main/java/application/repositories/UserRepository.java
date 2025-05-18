/**
 * @author Daniel Gil
 */
package application.repositories;

import application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface User repository
 * Esta interface estende JpaRepository, fornecendo uma abstração para acesso a dados.
 * JpaRepository já implementa a interface Repository, ou seja,
 * ela serve como uma camada intermediária entre a lógica de negócio da sua aplicação e o banco de dados.
 * JpaRepository já possui a anotação @Repository, então não é necessário colocar aqui.
 *
 * JpaRepository<User, Long>:
 * - User:  O tipo da entidade que este repositório gerencia (neste caso, User).
 * - Long:  O tipo do ID da entidade (neste caso, Long, que corresponde ao tipo do campo @Id em User).
 */
// @Repository // Não é necessário, pois JpaRepository já é anotado com @Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Esta interface herda métodos do JpaRepository, como:
    // - save(User entity):        Salva ou atualiza um usuário.
    // - findById(Long id):      Busca um usuário por ID, retornando um Optional<User>.
    // - findAll():             Busca todos os usuários.
    // - deleteById(Long id):    Deleta um usuário por ID.
    // - delete(User entity):    Deleta um usuário.
    // - existsById(Long id):   Verifica se um usuário com o ID existe.
    // - getReferenceById(Long id): Retorna uma referencia ao User pelo Id.
    // Não é necessário implementar esses métodos aqui; o Spring Data JPA os fornece automaticamente.

    // Se você precisar de métodos de consulta personalizados que não estão incluídos no JpaRepository,
    // você pode declará-los aqui.  Por exemplo:
    //
    //  Optional<User> findByEmail(String email);
    //  List<User> findByNameContaining(String name);
    //
    // O Spring Data JPA irá gerar a implementação desses métodos para você.
}
