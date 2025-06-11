/**
 * @author Daniel Gil
 */
package application.config;

import application.entities.*;
import application.entities.enums.OrderStatus;
import application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

/**
 * Class Teste de Configuração
 * Esta classe é responsável por configurar e popular o banco de dados com dados de teste
 * quando a aplicação é iniciada com o perfil "test".
 * Ela implementa a interface CommandLineRunner, que executa o metodo run()
 * após o contexto do Spring ser inicializado.
 */
@Configuration // Indica que esta classe é uma classe de configuração do Spring.
@Profile("test") // Indica que esta configuração só será carregada quando o perfil "test" estiver ativo.
public class TestConfig implements CommandLineRunner {

    // Injeção de Dependências (usando @Autowired para injeção de construtor - melhor prática)
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public TestConfig(UserRepository userRepository, OrderRepository orderRepository, CategoryRepository categoryRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * Metodo run() da interface CommandLineRunner.
     * Este metodo é executado automaticamente pelo Spring quando a aplicação é iniciada
     * com o perfil "test".  Ele contém a lógica para criar e salvar os dados de teste no banco.
     *
     * @param args Argumentos da linha de comando (não utilizados neste caso).
     * @throws Exception Pode lançar exceções durante a execução (tratadas pelo Spring).
     */
    @Override
    public void run(String... args) throws Exception {

        // Criação de instâncias de User (Usuário)
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        // Criação de instâncias de Order (Pedido)
        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1); // Pedido pago
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2); // Pedido aguardando pagamento
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1); // Pedido aguardando pagamento

        // Criação de instâncias de Category (Categoria)
        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        // Criação de instâncias de Product (Produto)
        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        // Salva os usuários no banco de dados
        userRepository.saveAll(Arrays.asList(u1, u2));
        // Salva os pedidos no banco de dados
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        // Salva as categorias no banco de dados
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        // Salva os produtos no banco de dados
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        // Associa produtos com categorias (ManyToMany)
        p1.getCategories().add(cat2); // Livro na categoria Livros
        p2.getCategories().add(cat1); // TV na categoria Eletrônicos
        p3.getCategories().add(cat3); // Macbook na categoria Computadores
        p4.getCategories().add(cat3); // PC Gamer na categoria Computadores
        p5.getCategories().add(cat2); // Livro na categoria Livros
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5)); // Salva novamente para persistir o relacionamento

        // Criação de instâncias de OrderItem (Item do Pedido) para associar produtos aos pedidos
        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); // 2 unidades do Livro no Pedido 1
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); // 1 unidade do Macbook no Pedido 1
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); // 2 unidades do Macbook no Pedido 2
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); // 2 unidades do Livro no Pedido 3
        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4)); // Salva os itens do pedido

        // Criação de Pagamento e associação com o Pedido 1
        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T22:53:07Z"), o1);
        o1.setPayment(pay1); // Associa o pagamento ao pedido
        orderRepository.save(o1); // Salva o pedido atualizado com o pagamento
    }
}
