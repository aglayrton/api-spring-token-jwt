package med.growdev.api.dto.paciente;

import jakarta.validation.constraints.NotNull;
import med.growdev.api.dto.endereco.DadosEndereco;
import med.growdev.api.domain.Paciente;


public record DadosAtualizadoPaciente(
        @NotNull //pois nao é uma string
        Long id,
        String nome,
        String phone,
        DadosEndereco endereco) {
        public DadosAtualizadoPaciente(Paciente paciente) {
                this(
                        paciente.getId(), paciente.getNome(), paciente.getPhone(), new DadosEndereco(paciente.getEndereco())
                );
        }
}
