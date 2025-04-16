package br.com.marcos.tarefas.controller;

import br.com.marcos.tarefas.model.Tarefa;
import br.com.marcos.tarefas.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public String listarTarefas(Model model, @ModelAttribute String errorMessage) {
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        model.addAttribute("tarefas", tarefas);
        if (!errorMessage.isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "listarTarefas";
    }

    @PostMapping("/adicionar")
    public String adicionarTarefa(@RequestParam String nome, @RequestParam double custo, @RequestParam String dataLimite) {
        tarefaService.adicionarTarefa(nome, custo, dataLimite);
        return "redirect:/tarefas";
    }

    @PostMapping("/editar")
    public String editarTarefa(@RequestParam String nome, @RequestParam double custo, @RequestParam String dataLimite, @RequestParam Long id) {
        tarefaService.editarTarefa(id, nome, custo, dataLimite);
        return "redirect:/tarefas";
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
        tarefaService.excluirTarefa(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/subir/{id}")
    public ResponseEntity<Void> subirTarefa(@PathVariable String id) {
        tarefaService.subir(Long.valueOf(id));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/descer/{id}")
    public ResponseEntity<Void> descerTarefa(@PathVariable String id) {
        tarefaService.descer(Long.valueOf(id));
        return ResponseEntity.ok().build();
    }
}
