package med.growdev.api.controller;

import jakarta.validation.Valid;
import med.growdev.api.dto.paciente.DadosAtualizadoPaciente;
import med.growdev.api.dto.paciente.DadosCadastroPaciente;
import med.growdev.api.dto.paciente.ListaPaciente;
import med.growdev.api.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping()
    public ResponseEntity<DadosCadastroPaciente> save(@Valid @RequestBody DadosCadastroPaciente dadosCadastroPaciente){
        DadosCadastroPaciente dadosCadastroPaciente1 = pacienteService.save(dadosCadastroPaciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosCadastroPaciente1);
    }

    @GetMapping ResponseEntity<Page<ListaPaciente>> list(Pageable pageable){
        Page resposta = pacienteService.listPage(pageable);
        return ResponseEntity.ok().body(resposta);
    }

    @PutMapping()
    public ResponseEntity<DadosAtualizadoPaciente> update(@Valid @RequestBody DadosAtualizadoPaciente dados){
        DadosAtualizadoPaciente dadosAtualizados = pacienteService.update(dados);
        return ResponseEntity.ok().body(dadosAtualizados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        //pacienteService.delete(id);
        pacienteService.disable(id);
        return ResponseEntity.noContent().build();
    }

}
