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
 *
 * @RestController classe Controlador
 * @RequestMapping mapeia caminho
 */
@RestController
@RequestMapping("/categories")
public class CategoryResource {

    // Injeção de Dependências
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * @param id
     * @return
     * @PathVariable é usada para extrair valores de variáveis
     * presentes na URL de uma requisição HTTP e injetá-los
     * como parâmetros em um  método de um controlador REST.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}

