/**
 * @author Daniel Gil
 */
package application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class Category
 * Esta classe representa a entidade "Category" (Categoria) no banco de dados.
 * Ela é mapeada para a tabela "tb_category".
 *
 * @Entity Indica que esta classe é uma entidade JPA.
 * @Table  Associa esta entidade à tabela "tb_category" no banco de dados.
 */
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @Id Chave Primária
     * @GeneratedValue Gerado automaticamente, utilizando a estratégia de identidade (geralmente do banco de dados).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    /**
     * @ManyToMany associação muitos-para-muitos (N-N)
     * @JoinTable associada com tabela criada na DB
     * @JsonIgnore evita o loop de mão dupla (lazy loading), um user tem varios orders
     * por sua vez a order tem um user, isso provoca um loop infinito se não ignorarmos.
     * mappedBy: Especifica o campo na entidade Product que detém a relação.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "categories") // Adicionado fetch = FetchType.LAZY
    private Set<Product> products = new HashSet<>();

    public Category() {
        // Construtor padrão necessário para JPA
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    // equals e hashCode baseados no ID para garantir a consistência do JPA e Hibernate

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
