package med.growdev.api.controller;

import jakarta.validation.Valid;
import med.growdev.api.dto.medico.DadosAtualizarMedico;
import med.growdev.api.dto.medico.DadosCadastroMedicox;
import med.growdev.api.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	@PostMapping
	public ResponseEntity<DadosCadastroMedicox> cadastrar(@Valid @RequestBody DadosCadastroMedicox dados,
	                                                      UriComponentsBuilder uriBuilder) {
		DadosCadastroMedicox md =
                medicoService.save(dados);
		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(md.id()).toUri();
		return ResponseEntity.created(uri).body(md);
		//return ResponseEntity.status(HttpStatus.CREATED).body(md);
	}

	@GetMapping
	public ResponseEntity<List<DadosCadastroMedicox.SimplifiedMedico>> listar() {
		List<DadosCadastroMedicox.SimplifiedMedico> mdList = medicoService.getAllSimplified();
		return ResponseEntity.ok().body(mdList);
	}

	@GetMapping("/paginado")
	public ResponseEntity<Page<DadosCadastroMedicox.SimplifiedMedico>> listarPaginado(Pageable paginacao) {
		Page<DadosCadastroMedicox.SimplifiedMedico> mdList = medicoService.getAllSimplifiedPageable(paginacao);
		return ResponseEntity.ok().body(mdList);
	}

	//paginacao customizada caso voce nao passe nada na
    // url ele traz o que tem no parametro do-
    // pageabledefault
	@GetMapping("/customizado")
	public ResponseEntity<Page<DadosCadastroMedicox.SimplifiedMedico>> listarPaginadoCustomizado(@PageableDefault(size = 3, sort = {"nome"}) Pageable paginacao) {
		Page<DadosCadastroMedicox.SimplifiedMedico> mdList = medicoService.getAllSimplifiedPageable(paginacao);
		return ResponseEntity.ok().body(mdList);
	}

	@PutMapping("/editar")
	public ResponseEntity<DadosCadastroMedicox> atualizar(@Valid @RequestBody DadosAtualizarMedico dados) {
		DadosCadastroMedicox dto =
                medicoService.update(dados);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable long id) {
		medicoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/desabilitar/{id}")
	public ResponseEntity<Void> desabilitar(@PathVariable long id) {
		medicoService.disable(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosCadastroMedicox.SimplifiedMedico> findById(@PathVariable long id) {
		DadosCadastroMedicox.SimplifiedMedico dto = medicoService.getFindId(id);
		return ResponseEntity.ok(dto);
	}
//  @GetMapping("/paginado")
//  public ResponseEntity<Page<DadosCadastroMedicox
//  .SimplifiedMedico>> listarPaginado(
//    @RequestParam(defaultValue = "0") int page,
//    @RequestParam(defaultValue = "10") int size) {
//    Page<DadosCadastroMedicox.SimplifiedMedico> mdList
//    = medicoService.getAllSimplifiedPageable
//    (PageRequest.of(page, size));
//    return ResponseEntity.ok().body(mdList);
//  }
}
