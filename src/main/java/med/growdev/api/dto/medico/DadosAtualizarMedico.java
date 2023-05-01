package med.growdev.api.dto.medico;

import jakarta.validation.constraints.NotNull;
import med.growdev.api.domain.Endereco;

public record DadosAtualizarMedico(
		@NotNull
		Long id,
		String nome,
		String telefone,
		Endereco endereco) {
}
