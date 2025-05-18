/**
 * @author Daniel Gil
 */
package application.resources;

import application.entities.Product;
import application.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class User Resource
 * Esta classe representa o controlador REST para a entidade Product (Produto).
 * Ela expõe os endpoints da API para realizar operações de leitura (Read)
 * na entidade Product. Os endpoints para criar, atualizar e deletar produtos
 * podem estar em outra classe de controlador, ou esta classe pode ser estendida.
 *
 * @RestController indica que esta classe é um controlador REST.
 * @RequestMapping mapeia o caminho base para todos os endpoints deste controlador.
 */
@RestController
@RequestMapping("/products")
public class ProductResource {

    // Injeção de Dependências
    // @Autowired injeta uma instância de ProductService. Isso permite que o controlador
    // use os métodos definidos na camada de serviço.
    @Autowired
    private ProductService service;

    /**
     * Endpoint para buscar todos os produtos.
     * @GetMapping mapeia a requisição HTTP GET para o caminho "/products".
     * @return ResponseEntity contendo a lista de produtos e o status HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Endpoint para buscar um produto pelo seu ID.
     * @param id O ID do produto a ser buscado.
     * @return ResponseEntity contendo o produto encontrado e o status HTTP 200 (OK),
     * ou o status HTTP 404 (Not Found) se o produto não existir.
     * @PathVariable é usada para extrair o valor da variável "id" presente na URL
     * e injetá-lo como parâmetro no método.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
