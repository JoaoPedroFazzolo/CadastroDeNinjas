package dev.java10x.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    private final MissoesService missoesService;
    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova missao", description = "Rota cria uma nova missao e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do missao")
    })
    public ResponseEntity<String> criarMissao(
            @Parameter(description = "Usuario envia as informaçoes da missao no corpo da requisiçao")
            @RequestBody MissoesDTO missoes
    ){
        MissoesDTO novaMissao = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criado com sucesso: " + novaMissao.toString() + " (ID): " + novaMissao.getId());
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista as missoes", description = "Rota lista as missoes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missoes listada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missoes nao encontradas")
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista uma missao pelo seu Id", description = "Rota lista uma missao pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao encontrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missao nao encontrada")
    })
    public ResponseEntity<?> listarMissaoPorId(
            @Parameter(description = "Usuario envia o Id no caminho da requisiçao")
            @PathVariable Long id
    ){
        MissoesDTO missoes = missoesService.listarMissoesPorId(id);
        if(missoes != null){
            return ResponseEntity.ok().body(missoes);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão com o ID " + id + " não foi encontrado.");
        }
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera uma missao pelo seu Id", description = "Rota altera uma missao pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missao nao encontrada, nao foi possivel alterar")
    })
    public ResponseEntity<?> alterarMissaoPorId(
            @Parameter(description = "Usuario envia o Id no caminho da requisicao")
            @PathVariable Long id,
            @Parameter(description = "Usuario envia dados da missao no corpo da requisicao")
            @RequestBody MissoesDTO missoesDTO
    ){
        MissoesDTO missoes = missoesService.atualizarMissoes(id, missoesDTO);
        if(missoes != null){
            return ResponseEntity.ok().body(missoes);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão com o ID " + id + " não foi encontrado.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta uma missao pelo seu Id", description = "Rota deleta uma missao pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missao não encontrada")
    })
    public ResponseEntity<String> deletarMissaoPorId(
            @Parameter(description = "Usuario envia o Id no caminho da requisiçao")
            @PathVariable Long id
    ){
        if(missoesService.listarMissoesPorId(id) != null){
            missoesService.deletarMissoesPorId(id);
            return ResponseEntity.ok("A missão com o ID " + id + " foi deletado com sucesso.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão com o ID " + id + " não foi encontrada.");
        }

    }
}
