/**
 * @author Daniel Gil
 */
package application.resources;

import application.entities.Category;
import application.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class User Resource
 * Esta classe representa o controlador REST para a entidade Category (Categoria).
 * Ela expõe os endpoints da API para realizar operações de leitura (Read)
 * na entidade Category. Os endpoints para criar, atualizar e deletar categorias
 * podem estar em outra classe de controlador, ou esta classe pode ser estendida.
 *
 * @RestController indica que esta classe é um controlador REST.
 * @RequestMapping mapeia o caminho base para todos os endpoints deste controlador.
 */
@RestController
@RequestMapping("/categories")
public class CategoryResource {

    // Injeção de Dependências
    // @Autowired injeta uma instância de CategoryService. Isso permite que o controlador
    // use os métodos definidos na camada de serviço.
    @Autowired
    private CategoryService service;

    /**
     * Endpoint para buscar todas as categorias.
     * @GetMapping mapeia a requisição HTTP GET para o caminho "/categories".
     * @return ResponseEntity contendo a lista de categorias e o status HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Endpoint para buscar uma categoria pelo seu ID.
     * @param id O ID da categoria a ser buscada.
     * @return ResponseEntity contendo a categoria encontrada e o status HTTP 200 (OK),
     * ou o status HTTP 404 (Not Found) se a categoria não existir.
     * @PathVariable é usada para extrair o valor da variável "id" presente na URL
     * e injetá-lo como parâmetro no método.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
