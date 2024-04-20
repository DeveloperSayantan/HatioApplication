package com.hatio.Service;

import java.util.List;

import com.hatio.Entity.ProjectEntity;

public interface ProjectService {
	
	ProjectEntity addProject(ProjectEntity projectEntity);
	
	ProjectEntity updateProject(int id, ProjectEntity projectEntity);
	
	List<ProjectEntity> fetchAllProject();
	
	ProjectEntity viewProjectById(int id);
}
