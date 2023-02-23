package med.growdev.api.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.growdev.api.dto.endereco.DadosEndereco;
import med.growdev.api.entities.Medico;

public record DadosCadastroMedicox(
    @NotBlank
    String nome,
    @NotBlank
    @Email
    String email,
    @NotBlank
    @Pattern(regexp = "\\d{4,6}") //quatro a seis digitos
    String crm,
    @NotNull //O SPRING JA TOMA CONTA DE ENUM, E É NOTNULL PORQUE NAO É UMA STRING
    Especialidade especialidade,
    @NotNull
    @Valid //diz para validar esse objeto
    DadosEndereco endereco) {
  public DadosCadastroMedicox(Medico medico) {
     this( medico.getNome(),
       medico.getEmail(),
       medico.getCrm(),
       medico.getEspecialidade(),
       new DadosEndereco(medico.getEndereco()));
  }

}
