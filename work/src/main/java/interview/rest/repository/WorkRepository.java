package interview.rest.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import interview.rest.entity.WorkEntity;

public interface WorkRepository extends JpaRepository<WorkEntity, Integer> {

	Page<WorkEntity> findAll(Pageable pageable);

	@SuppressWarnings("unchecked")
	WorkEntity save(WorkEntity workEntity);

	void deleteById(Integer id);

	Optional<WorkEntity> findById(Integer id);
}
