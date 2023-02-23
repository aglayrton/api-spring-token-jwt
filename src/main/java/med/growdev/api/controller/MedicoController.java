package med.growdev.api.controller;

import jakarta.validation.Valid;
import med.growdev.api.dto.medico.DadosCadastroMedicox;
import med.growdev.api.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

  @Autowired
  private MedicoService medicoService;

  @PostMapping
  public ResponseEntity<DadosCadastroMedicox> cadastrar(@Valid @RequestBody DadosCadastroMedicox dados){
    DadosCadastroMedicox md = medicoService.save(dados);
    return ResponseEntity.status(HttpStatus.CREATED).body(md);
  }

}
