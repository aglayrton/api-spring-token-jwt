package med.growdev.api.entities;

import jakarta.persistence.*;
import lombok.*;
import med.growdev.api.dto.medico.DadosCadastroMedicox;
import med.growdev.api.dto.medico.Especialidade;
@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //gera o equals e hash com base na id
public class Medico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;
  private String crm;
  @Enumerated(EnumType.STRING)
  private Especialidade especialidade;
  @Embedded //faz com que a classe endereço faz parte da classe de medicos
  private Endereco endereco;

  public Medico(DadosCadastroMedicox dados){
    this.nome = dados.nome();
    this.email = dados.email();
    this.crm = dados.crm();
    this.especialidade = dados.especialidade();
    this.endereco = new Endereco(dados.endereco());
  }

}
