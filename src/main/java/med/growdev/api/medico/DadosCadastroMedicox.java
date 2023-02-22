package med.growdev.api.medico;

import med.growdev.api.endereco.DadosEndereco;

public record DadosCadastroMedicox(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
