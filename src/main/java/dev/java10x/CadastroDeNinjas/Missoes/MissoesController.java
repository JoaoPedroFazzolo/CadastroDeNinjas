package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    private MissoesService missoesService;
    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoes){
        MissoesDTO novaMissao = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criado com sucesso: " + novaMissao.toString() + " (ID): " + novaMissao.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id){
        MissoesDTO missoes = missoesService.listarMissoesPorId(id);
        if(missoes != null){
            return ResponseEntity.ok().body(missoes);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão com o ID " + id + " não foi encontrado.");
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissaoPorId(@PathVariable Long id, @RequestBody MissoesDTO missoesDTO){
        MissoesDTO missoes = missoesService.atualizarMissoes(id, missoesDTO);
        if(missoes != null){
            return ResponseEntity.ok().body(missoes);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão com o ID " + id + " não foi encontrado.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissaoPorId(@PathVariable Long id){
        if(missoesService.listarMissoesPorId(id) != null){
            missoesService.deletarMissoesPorId(id);
            return ResponseEntity.ok("A missão com o ID " + id + " foi deletado com sucesso.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão com o ID " + id + " não foi encontrada.");
        }

    }
}
