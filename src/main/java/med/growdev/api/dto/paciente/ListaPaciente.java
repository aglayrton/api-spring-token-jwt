package med.growdev.api.dto.paciente;

import med.growdev.api.dto.endereco.DadosEndereco;
import med.growdev.api.domain.Paciente;

public record ListaPaciente(Long id, String nome, String email, String phone, DadosEndereco endereco) {
    public ListaPaciente(Paciente paciente){
        this(
                paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getPhone(), new DadosEndereco(paciente.getEndereco())
        );
    }
}
