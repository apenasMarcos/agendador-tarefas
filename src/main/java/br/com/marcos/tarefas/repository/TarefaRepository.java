package br.com.marcos.tarefas.repository;

import br.com.marcos.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findAllByOrderByOrdemAsc();
    Tarefa findByNome(String nome);
    Tarefa findByOrdem(int ordem);
    @Modifying
    @Query("UPDATE Tarefa t SET t.ordem = t.ordem - 1 WHERE t.ordem > :ordem")
    void updateOrdemSubsequente(@Param("ordem") int ordem);
}
