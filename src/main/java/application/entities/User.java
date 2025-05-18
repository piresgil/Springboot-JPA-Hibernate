/**
 * @author Daniel Gil
 */
package application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class User
 * Esta classe representa a entidade "User" (Usuário) no banco de dados.
 * Ela é mapeada para a tabela "tb_user".
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L; // Adicionado serialVersionUID para Serializable

    /**
     * @Id Chave Primária
     * @GeneratedValue Gerado automaticamente, utilizando a estratégia de identidade (geralmente do banco de dados).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;


    /**
     * @OneToMany associação um-para-muitos (1-N) (mapeado por)
     * @JsonIgnore evita o loop de mão dupla (lazy loading), um usuário tem vários pedidos
     * por sua vez, o pedido tem um usuário, isso provoca um loop infinito se não ignorarmos.
     * mappedBy: Especifica o campo na classe Order que detém a chave estrangeira para User.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>(); // Associação com a classe Order

    public User() {
        // Construtor padrão (sem argumentos) é necessário para JPA.
    }

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Getters e Setters para todos os atributos

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    // equals e hashCode baseados no ID para garantir a consistência do JPA e Hibernate

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
