package com.hatio.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hatio.Entity.ProjectEntity;
import com.hatio.Repository.ProjectRepo;
import com.hatio.Service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepo projectRepo;
	
	@Override
	public ProjectEntity addProject(ProjectEntity projectEntity) {
		
		return projectRepo.save(projectEntity);
	}

	@Override
	public ProjectEntity updateProject(int id, ProjectEntity projectEntity) {

		Optional<ProjectEntity> existProject = projectRepo.findById(id);
		if(existProject.isPresent()) {
			
			ProjectEntity currentProject = existProject.get();
			
			currentProject.setTitle(projectEntity.getTitle()); //Update project title.
			
			return projectRepo.save(currentProject);
		}
		return null;
	}

	@Override
	public List<ProjectEntity> fetchAllProject() {
		
		return projectRepo.findAll();
	}

	@Override
	public ProjectEntity viewProjectById(int id) {
		
		return projectRepo.findById(id).get();
	}

}
