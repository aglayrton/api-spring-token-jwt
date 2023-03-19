package med.growdev.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.growdev.api.domain.Endereco;

public record DadosEndereco(
  @NotBlank
  String logradouro,
  @NotBlank
  String bairro,
  @NotBlank
  @Pattern(regexp = "\\d{8}") //ok
  String cep,
  @NotBlank
  String cidade,
  @NotBlank
  String uf,
  String complemento,
  String numero) {
  public DadosEndereco(Endereco endereco){
    this(endereco.getLogradouro(),endereco.getBairro(),endereco.getCep(),endereco.getCidade(),endereco.getUf(),
    endereco.getComplemento(),
     endereco.getNumero());
  }
}
