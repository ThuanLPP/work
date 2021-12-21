package interview.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import interview.rest.entity.WorkEntity;
import interview.rest.model.WorkModel;
import interview.rest.service.WorkService;

@RestController
public class WorkController {

	@Autowired
	private WorkService workService;

	@RequestMapping("/work")
	public ModelAndView init(@RequestParam Optional<Integer> page,
			@RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
			@RequestParam(value = "sort", defaultValue = "id", required = false) String sortProperty) {
		int currentPage = page.orElse(1);
		int pageSize = 5;

		Page<WorkEntity> workPage = workService
				.getWorks(PageRequest.of(currentPage - 1, pageSize, Sort.by(direction, sortProperty)));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("workPage", workPage);
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("order", direction.toString());
		modelAndView.addObject("sort", sortProperty);

		// display screen
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping("/showNewWork")
	public ModelAndView showNewWork() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("workModel", new WorkModel());
		modelAndView.setViewName("newWork");
		return modelAndView;
	}

	@PostMapping("/saveWork")
	public ModelAndView saveWork(@ModelAttribute("workModel") WorkModel workModel) {

		// add new
		workService.saveWork(workModel);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/work");
		return modelAndView;
	}

	@RequestMapping("/updateWork")
	public ModelAndView updateWork(@RequestParam Integer id) {
		WorkModel workModel = workService.getById(id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editWork");
		modelAndView.addObject("workModel", workModel);
		return modelAndView;
	}

	@PostMapping("/editWork")
	public ModelAndView editWork(@ModelAttribute("workModel") WorkModel workModel) {

		// add new
		workService.update(workModel);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/work");
		return modelAndView;
	}

	@RequestMapping("/deleteWork")
	public ModelAndView deleteWork(@RequestParam Integer id) {

		// delete
		workService.deleteWork(id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/work");
		return modelAndView;
	}

}
