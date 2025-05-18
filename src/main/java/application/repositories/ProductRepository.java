/**
 * @author Daniel Gil
 */
package application.repositories;

import application.entities.Product;
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
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Esta interface herda métodos do JpaRepository, como:
    // - save(Product entity):        Salva ou atualiza um produto.
    // - findById(Long id):      Busca um produto por ID, retornando um Optional<Product>.
    // - findAll():             Busca todos os produtos.
    // - deleteById(Long id):    Deleta um produto por ID.
    // - delete(Product entity):    Deleta um produto.
    //
    // Não é necessário implementar esses métodos aqui; o Spring Data JPA os fornece automaticamente.

    // Se você precisar de métodos de consulta personalizados que não estão incluídos no JpaRepository,
    // você pode declará-los aqui.  Por exemplo:
    //
    //  List<Product> findByNameContaining(String name);
    //  List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    //
    // O Spring Data JPA irá gerar a implementação desses métodos para você.
}
