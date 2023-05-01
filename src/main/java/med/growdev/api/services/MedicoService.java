package med.growdev.api.services;

import med.growdev.api.domain.Endereco;
import med.growdev.api.domain.Medico;
import med.growdev.api.dto.medico.DadosAtualizarMedico;
import med.growdev.api.dto.medico.DadosCadastroMedicox;
import med.growdev.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

  public List<DadosCadastroMedicox.SimplifiedMedico> getAllSimplified() {
    List<Medico> mdL = repository.findAll();
    return mdL.stream().map(m -> new DadosCadastroMedicox(m).toSimplifiedMedico()).collect(Collectors.toList());
  }

  public Page<DadosCadastroMedicox.SimplifiedMedico> getAllSimplifiedPageable(Pageable page) {
    Page<Medico> mdL = repository.findAllByAtivoTrue(page);
    return mdL.map(m -> new DadosCadastroMedicox.SimplifiedMedico(m.getNome(), m.getEmail(), m.getCrm(), m.getEspecialidade()));
  }

  public DadosCadastroMedicox.SimplifiedMedico getFindId(Long id){
    Medico medico = repository.getReferenceById(id);
    return new DadosCadastroMedicox.SimplifiedMedico(medico);
  }

  public DadosCadastroMedicox update(DadosAtualizarMedico dados) {
    Medico medico = repository.getReferenceById(dados.id());
    //se voce carrega uma entidade do banco de dados e mud algum atributo, quando a transacao for comitada
    //a jpa detecta que teve uma mudaça no atributo e faz o update sozinho
    medico.atualizarDados(dados);
    return new DadosCadastroMedicox(medico);
  }

  public void delete(Long id){
    repository.deleteById(id);
  }

  public void disable(Long id){
    Medico medico = repository.getReferenceById(id);
    medico.setAtivo(false);
  }
}

//  public Page<DadosCadastroMedicox.SimplifiedMedico> getAllSimplifiedPageable(Pageable page) {
//    Page<Medico> mdL = repository.findAll(page);
//    List<DadosCadastroMedicox.SimplifiedMedico> simplifiedList = mdL
//      .stream()
//      .map(m -> new DadosCadastroMedicox(m).toSimplifiedMedico())
//      .collect(Collectors.toList());
//    return new PageImpl<>(simplifiedList, page, mdL.getTotalElements());
//  }
