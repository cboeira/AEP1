package tarefas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tarefas extends JpaRepository<Tarefa, Long> {



}