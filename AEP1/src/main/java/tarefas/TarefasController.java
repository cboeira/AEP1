package tarefas;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tarefa")
@RestController 
public class TarefasController {
	
	@Autowired
	private Tarefas tarefas;
	
//	@RequestMapping("/tarefa")
	@PostMapping
	public Tarefa adicionarTarefa(@Valid @RequestBody Tarefa tarefa) { 
		 return tarefas.save(tarefa); 
	}
	
//	@RequestMapping("/tarefas")
	@GetMapping
	public List<Tarefa> listar() {
			return tarefas.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
		Tarefa tarefa = tarefas.findById(id).orElse(null);
		if (tarefa == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tarefa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
		Tarefa atual = tarefas.findById(id).orElse(null);
		
		if (atual == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(tarefa,  atual, "id");
		atual = tarefas.save(atual);
		
		return ResponseEntity.ok(atual);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Tarefa tarefa = tarefas.findById(id).orElse(null);
		if (tarefa == null) {
			return ResponseEntity.notFound().build();
		}
		
		tarefas.delete(tarefa);
		return ResponseEntity.noContent().build();
	}
	 
}