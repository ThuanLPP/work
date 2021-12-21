package interview.rest.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import interview.rest.entity.WorkEntity;
import interview.rest.model.WorkModel;
import interview.rest.repository.WorkRepository;

@Service
public class WorkService {
	@Autowired
	private WorkRepository workRepository;

	/*
	 * Read
	 */
	public Page<WorkEntity> getWorks(Pageable pageable) {
		Page<WorkEntity> workPage = workRepository.findAll(pageable);
		return workPage;
	}

	/*
	 * Create
	 */
	public void saveWork(WorkModel workModel) {
		if (workModel != null) {
			ModelMapper mapper = new ModelMapper();
			WorkEntity workEntity = mapper.map(workModel, WorkEntity.class);
			workRepository.save(workEntity);
		}
	}

	/*
	 * Delete
	 */
	public void deleteWork(Integer id) {
		workRepository.deleteById(id);
	}

	/*
	 * Update
	 */
	public void update(WorkModel workModel) {
		if (workModel != null) {
			WorkEntity workEntity = workRepository.getById(workModel.getId());
			if (workEntity != null) {
				workEntity.setWorkName(workModel.getWorkName());
				workEntity.setStatus(workModel.getStatus());
				workEntity.setStartDate(workModel.getStartDate());
				workEntity.setEndDate(workModel.getEndDate());

				workRepository.save(workEntity);
			}
		}
	}

	public WorkModel getById(Integer id) {
		WorkEntity workEntity = workRepository.getById(id);
		ModelMapper mapper = new ModelMapper();
		WorkModel workModel = mapper.map(workEntity, WorkModel.class);
		return workModel;
	}
}
