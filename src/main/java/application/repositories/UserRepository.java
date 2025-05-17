/**
 * @author Daniel Gil
 */
package application.repositories;

import application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface User repository
 * extends JPA Repository, Function<>
 * que abstrai o acesso a dados.
 * Ele serve como uma camada intermediária entre a lógica de negócio da sua aplicação e o banco de dados.
 * JpaRepository ja tem @Repository
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
