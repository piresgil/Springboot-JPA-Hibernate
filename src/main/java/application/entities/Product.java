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
 * Class Product
 * Esta classe representa a entidade "Product" (Produto) no banco de dados.
 * Ela é mapeada para a tabela "tb_product".
 */
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do produto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    /**
     * Relacionamento muitos-para-muitos com a entidade Category.
     * Esta anotação define a tabela intermediária que armazena o relacionamento entre Product e Category.
     */
    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"), // Coluna na tabela intermediária que referencia Product
            inverseJoinColumns = @JoinColumn(name = "category_id")) // Coluna na tabela intermediária que referencia Category
    private Set<Category> categories = new HashSet<>(); // Associação com a entidade Category

    /**
     * Relacionamento um-para-muitos com a entidade OrderItem.
     * mappedBy: Especifica o campo na classe OrderItem que detém a chave estrangeira para Product.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>(); // Associação com a entidade OrderItem

    /**
     * Construtor padrão necessário para JPA.
     */
    public Product() {
        // Construtor padrão necessário para JPA
    }

    /**
     * Construtor da classe Product.
     *
     * @param id          O ID do produto.
     * @param name        O nome do produto.
     * @param description A descrição do produto.
     * @param price       O preço do produto.
     * @param imgUrl      A URL da imagem do produto.
     */
    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    /**
     * Obtém o ID do produto.
     *
     * @return O ID do produto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     *
     * @param id O ID do produto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return O nome do produto.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do produto.
     *
     * @param name O nome do produto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém a descrição do produto.
     *
     * @return A descrição do produto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição do produto.
     *
     * @param description A descrição do produto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtém o preço do produto.
     *
     * @return O preço do produto.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Define o preço do produto.
     *
     * @param price O preço do produto.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Obtém a URL da imagem do produto.
     *
     * @return A URL da imagem do produto.
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Define a URL da imagem do produto.
     *
     * @param imgUrl A URL da imagem do produto.
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Obtém as categorias às quais o produto pertence.
     *
     * @return As categorias do produto.
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /**
     * Obtém os itens do pedido associados a este produto.
     *
     * @return Os itens do pedido.
     */
    public Set<OrderItem> getItems() {
        return items;
    }

    /**
     * Obtém os pedidos associados a este produto através dos OrderItems.
     * O uso de @JsonIgnore evita a serialização deste método no JSON, prevenindo loops
     * infinitos em relacionamentos cíclicos.
     *
     * @return Os pedidos associados ao produto.
     */
    @JsonIgnore
    public Set<Order> getOrders() {
        Set<Order> set = new HashSet<>();
        for (OrderItem x : items) {
            set.add(x.getOrder());
        }
        return set;
    }

    /**
     * Verifica se dois objetos Product são iguais.
     * A igualdade é baseada no ID do produto.
     *
     * @param o O objeto a comparar com este produto.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    /**
     * Calcula o código hash para este produto.
     * O código hash é baseado no ID do produto.
     *
     * @return O código hash do produto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}