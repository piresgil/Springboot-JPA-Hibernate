/**
 * @author Daniel Gil
 */
package application.resources;

import application.entities.User;
import application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Class User Resource
 * Esta classe representa o controlador REST para a entidade User (Usuário).
 * Ela expõe os endpoints da API para realizar operações CRUD (Create, Read, Update, Delete)
 * na entidade User.
 *
 * @RestController indica que esta classe é um controlador REST.
 * @RequestMapping mapeia o caminho base para todos os endpoints deste controlador.
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    // Injeção de Dependências
    // @Autowired injeta uma instância de UserService.  Isso permite que o controlador
    // use os métodos definidos na camada de serviço.
    @Autowired
    private UserService service;

    /**
     * Endpoint para buscar todos os usuários.
     * @GetMapping mapeia a requisição HTTP GET para o caminho "/users".
     * @return ResponseEntity contendo a lista de usuários e o status HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Endpoint para buscar um usuário pelo seu ID.
     * @param id O ID do usuário a ser buscado.
     * @return ResponseEntity contendo o usuário encontrado e o status HTTP 200 (OK),
     * ou o status HTTP 404 (Not Found) se o usuário não existir.
     * @PathVariable é usada para extrair o valor da variável "id" presente na URL
     * e injetá-lo como parâmetro no método.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    /**
     * Endpoint para inserir um novo usuário.
     * @param obj O objeto User contendo os dados do novo usuário.
     * @return ResponseEntity contendo o usuário criado e o status HTTP 201 (Created),
     * além do cabeçalho "Location" com a URI do novo recurso criado.
     * @RequestBody indica que o objeto User vem no corpo da requisição HTTP.
     */
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        obj = service.insert(obj);
        // Constrói a URI para o novo recurso criado (ex: /users/3)
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    /**
     * Endpoint para deletar um usuário pelo seu ID.
     * @param id O ID do usuário a ser deletado.
     * @return ResponseEntity com o status HTTP 204 (No Content), indicando que a
     * operação foi realizada com sucesso, mas não há conteúdo para retornar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para atualizar um usuário existente.
     * @param id O ID do usuário a ser atualizado.
     * @param obj O objeto User com os novos dados do usuário.
     * @return ResponseEntity contendo o usuário atualizado e o status HTTP 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
