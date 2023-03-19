package med.growdev.api.services;

import med.growdev.api.dto.paciente.DadosAtualizadoPaciente;
import med.growdev.api.dto.paciente.DadosCadastroPaciente;
import med.growdev.api.dto.paciente.ListaPaciente;
import med.growdev.api.domain.Endereco;
import med.growdev.api.domain.Paciente;
import med.growdev.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public DadosCadastroPaciente save(DadosCadastroPaciente dadosCadastroPaciente){
        Paciente paciente = new Paciente(dadosCadastroPaciente);
        paciente = repository.save(paciente);
        return new DadosCadastroPaciente(paciente);
    }

    public Page<ListaPaciente> listPage(Pageable page){
        Page<Paciente> pagePacientes = repository.findAllByEnableTrue(page);
        return pagePacientes.map(ListaPaciente::new);
    }

    public DadosAtualizadoPaciente update(DadosAtualizadoPaciente dadosCadastroPaciente) {
        Paciente paciente = repository.getReferenceById(dadosCadastroPaciente.id());
        paciente.setNome(dadosCadastroPaciente.nome());
        paciente.setPhone(dadosCadastroPaciente.phone());
        paciente.setEndereco(new Endereco(dadosCadastroPaciente.endereco()));
        paciente = repository.saveAndFlush(paciente);
        return new DadosAtualizadoPaciente(paciente);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public void disable(long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.setEnable(false);
    }
}
