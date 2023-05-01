package med.growdev.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.growdev.api.dto.medico.DadosAtualizarMedico;
import med.growdev.api.dto.medico.DadosCadastroMedicox;
import med.growdev.api.dto.medico.Especialidade;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//gera o equals e hash com base na id
public class Medico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;
  private String crm;
  private Boolean ativo;
  @Enumerated(EnumType.STRING)
  private Especialidade especialidade;
  @Embedded //faz com que a tabela endereço faz parte da tabela de medicos
  private Endereco endereco;

  private String telefone;

  public Medico(DadosCadastroMedicox dados){
    this.ativo = true;
    this.nome = dados.nome();
    this.email = dados.email();
    this.crm = dados.crm();
    this.especialidade = dados.especialidade();
    this.telefone = dados.telefone();
    this.endereco = new Endereco(dados.endereco());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Medico medico = (Medico) o;
    return getId() != null && Objects.equals(getId(), medico.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public void atualizarDados(DadosAtualizarMedico medico) {
    if(medico.nome() != null){
      this.nome = medico.nome();
    }
    if(medico.telefone() != null){
      this.telefone = medico.telefone();
    }
    if(medico.endereco() != null){
      this.endereco.atualizarDados(medico.endereco());
    }
  }
}
