package med.growdev.api.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.growdev.api.dto.endereco.DadosEndereco;
import med.growdev.api.domain.Paciente;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroPaciente(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @CPF
        String cpf,
        @NotNull
                @Valid
        DadosEndereco endereco) {

        public DadosCadastroPaciente(Paciente paciente){
                this(
                        paciente.getNome(),
                        paciente.getEmail(),
                        paciente.getCpf(),
                        paciente.getPhone(),
                        new DadosEndereco(paciente.getEndereco())
                );
        }
}
