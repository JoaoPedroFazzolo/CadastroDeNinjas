package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    public List<MissoesModel> listarMissoes(){
        return missoesRepository.findAll();
    }

    public MissoesModel listarMissoesPorId(Long id){
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.orElse(null);
    }

    public MissoesModel criarMissoes(MissoesModel missoes){
        return missoesRepository.save(missoes);
    }

    public void deletarMissoesPorId(Long id){
        missoesRepository.deleteById(id);
    }

    public MissoesModel atualizarMissoes(Long id, MissoesModel missoes){
        if (missoesRepository.existsById(id)){
            missoes.setId(id);
            return missoesRepository.save(missoes);
        }
        return null;
    }
}
