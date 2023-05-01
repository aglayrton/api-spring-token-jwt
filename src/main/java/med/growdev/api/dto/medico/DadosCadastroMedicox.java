package med.growdev.api.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.growdev.api.domain.Endereco;
import med.growdev.api.dto.endereco.DadosEndereco;
import med.growdev.api.domain.Medico;

public record DadosCadastroMedicox(Long id, @NotBlank String nome, @NotBlank @Email String email,
   @NotBlank @Pattern(regexp = "\\d{4,6}") //quatro a seis digitos
   String crm,

   @NotNull //O SPRING JA TOMA CONTA DE ENUM, E É NOTNULL PORQUE NAO É UMA ‘STRING’
   Especialidade especialidade,

   @NotBlank String telefone,

   @NotNull @Valid //diz para validar esse objeto
   DadosEndereco endereco) {

	public DadosCadastroMedicox(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(),
				medico.getTelefone(), new DadosEndereco(medico.getEndereco()));
	}

	// Você pode por método na classe DadosCadastroMedicoxque retorna um objeto simplificado, contendo somente os
	// campos
	// que você deseja exibir.
	public SimplifiedMedico toSimplifiedMedico() {
		return new SimplifiedMedico(nome, email, crm, especialidade);
	}

	//  Essa classe foi criada como um registro interno, o qual uma classe simples que contém somente campos e nenhum
	//  método adicional.
	public record SimplifiedMedico(String nome, String email, String crm, Especialidade especialidade) {
		public SimplifiedMedico(Medico medico) {
			this(
					medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade()
			);
		}
	}


}
