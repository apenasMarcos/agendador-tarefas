package br.com.marcos.tarefas.service;

import br.com.marcos.tarefas.model.Tarefa;
import br.com.marcos.tarefas.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaServiceImpl(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }


    @Override
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAllByOrderByOrdemAsc();
    }

    @Override
    @Transactional
    public void adicionarTarefa(String nome, double custo, String dataLimite) {
        if (buscarTarefaPorNome(nome).isPresent()) {
            throw new IllegalArgumentException("Tarefa com este nome já existe");
        }

        Tarefa tarefa = new Tarefa();
        tarefa.setNome(nome);
        tarefa.setCusto(custo);
        tarefa.setDataLimite(LocalDate.parse(dataLimite));
        tarefa.setOrdem((int) (tarefaRepository.count() + 1));

        tarefaRepository.save(tarefa);
    }

    @Override
    @Transactional
    public void editarTarefa(Long id, String nome, double custo, String dataLimite) {
        Tarefa tarefa = buscarTarefaPorId(id);

        tarefa.setNome(nome);
        tarefa.setCusto(custo);
        tarefa.setDataLimite(LocalDate.parse(dataLimite));

        tarefaRepository.save(tarefa);
    }

    @Override
    @Transactional
    public void excluirTarefa(Long id) {
        Tarefa tarefaExcluir = buscarTarefaPorId(id);
        tarefaRepository.updateOrdemSubsequente(tarefaExcluir.getOrdem());
        tarefaRepository.delete(tarefaExcluir);
    }

    @Override
    @Transactional
    public void subir(Long tarefaId) {
        Tarefa tarefa = buscarTarefaPorId(tarefaId);
        Tarefa tarefaAnterior = buscarTarefaAdjacente(tarefa.getOrdem() - 1);

        if (tarefaAnterior != null) {
            trocarOrdemTarefas(tarefa, tarefaAnterior);
        } else {
            throw new IllegalArgumentException("Não há tarefa acima para mover.");
        }
    }

    @Override
    @Transactional
    public void descer(Long tarefaId) {
        Tarefa tarefa = buscarTarefaPorId(tarefaId);
        Tarefa tarefaSeguinte = buscarTarefaAdjacente(tarefa.getOrdem() + 1);

        if (tarefaSeguinte != null) {
            trocarOrdemTarefas(tarefa, tarefaSeguinte);
        } else {
            throw new IllegalArgumentException("Não há tarefa abaixo para mover.");
        }
    }

    public Optional<Tarefa> buscarTarefaPorNome(String nome) {
        return Optional.ofNullable(tarefaRepository.findByNome(nome));
    }

    private Tarefa buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com ID: " + id));
    }

    private Tarefa buscarTarefaAdjacente(int ordem) {
        return tarefaRepository.findByOrdem(ordem);
    }

    private void trocarOrdemTarefas(Tarefa tarefa1, Tarefa tarefa2) {
        int tempOrdem = tarefa1.getOrdem();
        tarefa1.setOrdem(tarefa2.getOrdem());
        tarefa2.setOrdem(tempOrdem);

        tarefaRepository.save(tarefa1);
        tarefaRepository.save(tarefa2);
    }
}