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
 */

/**
 * @Entity
 * @Table associada a tabela criada na DB
 */
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    /**
     * @Id Chave Primária
     * @GeneratedValue Gerado auto, segundo estratégia
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    /**
     * @ManyToMany associação muitas para muitas
     * @JoinTable associada com tabela criada na DB
     * @JsonIgnore evita o loop de mão dupla (Lazy Loading), um user tem varios orders
     * por sua ves a order tem um user, isso provoca um loop infinito
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
