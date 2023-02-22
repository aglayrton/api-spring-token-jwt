package med.growdev.api.controller;

import med.growdev.api.medico.DadosCadastroMedicox;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
  @PostMapping
  public void cadastrar(@RequestBody DadosCadastroMedicox dados){
    System.out.println("dados = " + dados);
  }

}
