package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUI {

    private final MissoesService missoesService;

    public MissoesControllerUI(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarMissoes(Model model){
        model.addAttribute("missoes", new MissoesDTO());
        return "adicionarMissoes";
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    @GetMapping("/listar/{id}")
    public String listarMissaoPorId(@PathVariable Long id, Model model){
        MissoesDTO missoes = missoesService.listarMissoesPorId(id);
        if(missoes != null){
            model.addAttribute("missoes", missoes);
            return "detalhesMissoes";
        }else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "listarMissoes";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissaoPorId(@PathVariable Long id){
        missoesService.deletarMissoesPorId(id);
        return "redirect:/missoes/ui/listar";

    }

    @PostMapping("/salvar")
    public String salvarMissoes(@ModelAttribute MissoesDTO missoesDTO, RedirectAttributes redirectAttributes){
        missoesService.criarMissoes(missoesDTO);
        redirectAttributes.addFlashAttribute("mensagem", "Missão cadastrado com sucesso");
        return "redirect:/missoes/ui/listar";
    }
}
