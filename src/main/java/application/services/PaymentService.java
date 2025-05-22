/**
 * @author Daniel Gil
 */
package application.services;

import application.entities.Payment;
import application.repositories.PaymentRepository;
import application.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class Order Service
 * Esta classe representa a camada de serviço para a entidade Payment (Categoria).
 * Ela contém a lógica de negócios para realizar operações relacionadas à entidade Payment.
 * Neste caso, as operações estão limitadas a leitura (Read).
 *
 * @Service indica que esta classe é um componente de serviço do Spring.
 */
@Service
public class PaymentService {

    // Injeção de Dependências
    // @Autowired injeta uma instância de PaymentRepository. Isso permite que a classe de serviço
    // interaja com o banco de dados através do repositório.
    @Autowired
    private PaymentRepository repository;

    /**
     * Busca todas as categorias.
     * @return Uma lista contendo todas as categorias cadastradas.
     */
    public List<Payment> findAll() {
        return repository.findAll();
    }

    /**
     * Busca uma categoria pelo seu ID.
     * Optional<Payment> é uma classe introduzida no Java 8
     * com o objetivo de fornecer uma forma mais segura
     * e expressiva de lidar com valores que podem ser nulos.
     * Ela encapsula um valor opcional, indicando se um valor está presente ou não.
     * Isso ajuda a evitar o famigerado NullPointerException, um dos erros mais comuns em Java.
     *
     * @param id O ID da categoria a ser buscada.
     * @return A categoria encontrada.
     */
    public Payment findById(Long id) {
        Optional<Payment> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
        // obj.orElseThrow(() -> new ResourceNotFoundException(id)); //Forma mais concisa de retornar o objeto ou lançar a exceção
    }
}
