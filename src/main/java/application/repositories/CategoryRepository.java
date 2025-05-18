/**
 * @author Daniel Gil
 */
package application.repositories;

import application.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Order repository
 * Esta interface estende JpaRepository, fornecendo uma abstração para acesso a dados.
 * JpaRepository já implementa a interface Repository, ou seja,
 * ela serve como uma camada intermediária entre a lógica de negócio da sua aplicação e o banco de dados.
 * JpaRepository já possui a anotação @Repository, então não é necessário colocar aqui.
 */
// @Repository // Não é necessário, pois JpaRepository já é anotado com @Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Esta interface herda métodos do JpaRepository, como:
    // - save(Category entity):        Salva ou atualiza uma categoria.
    // - findById(Long id):      Busca uma categoria por ID, retornando um Optional<Category>.
    // - findAll():             Busca todas as categorias.
    // - deleteById(Long id):    Deleta uma categoria por ID.
    // - delete(Category entity):    Deleta uma categoria.
    //
    // Não é necessário implementar esses métodos aqui; o Spring Data JPA os fornece automaticamente.

    // Se você precisar de métodos de consulta personalizados que não estão incluídos no JpaRepository,
    // você pode declará-los aqui.  Por exemplo:
    //
    //  Optional<Category> findByName(String name);
    //
    // O Spring Data JPA irá gerar a implementação desses métodos para você.
}
