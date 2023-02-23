package med.growdev.api.services;

import med.growdev.api.entities.Medico;
import med.growdev.api.dto.medico.DadosCadastroMedicox;
import med.growdev.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicoService {
  @Autowired
  private MedicoRepository repository;

  public DadosCadastroMedicox save(DadosCadastroMedicox dto){
    Medico medico = new Medico(dto);
    medico = repository.save(medico);
    return new DadosCadastroMedicox(medico);
  }
}
