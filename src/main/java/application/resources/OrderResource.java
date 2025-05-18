/**
 * @author Daniel Gil
 */
package application.resources;

import application.entities.Order;
import application.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class User Resource
 * Esta classe representa o controlador REST para a entidade Order (Pedido).
 * Ela expõe os endpoints da API para realizar operações de leitura (Read)
 * na entidade Order.  Os endpoints para criar, atualizar e deletar pedidos
 * podem estar em outra classe de controlador, ou esta classe pode ser estendida.
 *
 * @RestController indica que esta classe é um controlador REST.
 * @RequestMapping mapeia o caminho base para todos os endpoints deste controlador.
 */
@RestController
@RequestMapping("/orders")
public class OrderResource {

    // Injeção de Dependências
    // @Autowired injeta uma instância de OrderService. Isso permite que o controlador
    // use os métodos definidos na camada de serviço.
    @Autowired
    private OrderService service;

    /**
     * Endpoint para buscar todos os pedidos.
     * @GetMapping mapeia a requisição HTTP GET para o caminho "/orders".
     * @return ResponseEntity contendo a lista de pedidos e o status HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Endpoint para buscar um pedido pelo seu ID.
     * @param id O ID do pedido a ser buscado.
     * @return ResponseEntity contendo o pedido encontrado e o status HTTP 200 (OK),
     * ou o status HTTP 404 (Not Found) se o pedido não existir.
     * @PathVariable é usada para extrair o valor da variável "id" presente na URL
     * e injetá-lo como parâmetro no método.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
