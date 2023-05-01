package med.growdev.api.repositories;

import med.growdev.api.domain.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
	//List<Medico> findAllByAtivoTrue();

	Page<Medico> findAllByAtivoTrue(Pageable page);
}
