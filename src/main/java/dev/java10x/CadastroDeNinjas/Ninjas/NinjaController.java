package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar ninja
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado";
    }

    // mostrar ninja por ID
    @GetMapping("/todosID")
    public String mostrarTodosOsNinjasPorId(){
        return "Todos os ninjas por ID";
    }

    // mostrar todos ninjas
    @GetMapping("/todos")
    public String mostrarTodosOsNinjas(){
        return "Todos os ninjas";
    }

    // aletrar dados dos ninjas
    @PutMapping("/alterarID")
    public String alterarNinjaPorId(){
        return "Ninja alterado por ID";
    }

    // deletar ninja
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId(){
        return "Ninja deletado por ID";
    }

}