package com.hatio.WebController;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.EntityProjection.ProjectionType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hatio.Entity.ProjectEntity;
import com.hatio.Entity.TodoEntity;
import com.hatio.Entity.UserEntity;
import com.hatio.Service.ProjectService;
import com.hatio.Service.TodoService;
import com.hatio.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class webControlHandler {

	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	TodoService todoService;
	
	@GetMapping("/index")
	public String indexPage() {
		
		return "index";
	}
	
	//***************************Register*********************************
	@GetMapping("/register")
	public String dispalyForm(Model model) {

		model.addAttribute("customerForm", new UserEntity());
		return "register";
	}

	@PostMapping("/registerSave")
	public String registerCustomer(@ModelAttribute UserEntity userEntity, Model model) {
		
	        System.out.println("Email: " + userEntity.getEmail());
	        System.out.println("Password: " + userEntity.getPassword());
	        
	        
	        
	        if(userService.isEmailExists(userEntity.getEmail())) {
	        	
	        	model.addAttribute("errorEmail", "Email already exists!");
		           
	            return "register";
	        }
	        
	        userService.addUser(userEntity);
	        model.addAttribute("userForm",new UserEntity());
	        model.addAttribute("success", "Registration successful!");
	        
	        return "register";
}
	
	//****************************Login*********************************
	
	@PostMapping("/loginCustomer")
	public String login(@RequestParam String email,@RequestParam String password,HttpSession session,Model model) {
		
		//System.out.println(inputCsmailpass.getEmail()+" "+inputCsmailpass.getPassword());
		
		UserEntity user = userService.loginByEmail(email);
		
		if( user != null && user.getPassword().equals(password)) {
			
			session.setAttribute("user", user.getEmail());
			session.setAttribute("userId", user.getId());
			return "dashboard";
		}
			else {
	            // Failed login
	            model.addAttribute("error", "Invalid email or password");
	           
	            return "index";
		}
		
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
	    session.setAttribute("logoutMessage", "Successfully Logout");
	    session.invalidate();
	    return "redirect:/index";
	}
	
//*****************************Create Project*********************
	@PostMapping("/create")
	public String createProject(@ModelAttribute ProjectEntity projectEntity,
				Model model) {
	    		
	    		projectService.addProject(projectEntity);

	    		model.addAttribute("msg", "Project added Successfully!");
	    	return "dashboard";
	    	
	}
	
	@GetMapping("/project")
    public String viewAllProject(Model model) {
		
		model.addAttribute("projectDetails",projectService.fetchAllProject());
    	return "project";
    }
	
//************Todos******************************

    @GetMapping("/viewDetails/{id}")
    public String viewProjectDetails(Model model, @PathVariable("id") int id) {
    	
        ProjectEntity project = projectService.viewProjectById(id);
        
        model.addAttribute("project", project);
        
        return "viewDetails"; 
    }
    
    @GetMapping("/viewTodo/{projectId}")
    public String todoPage(Model model, @PathVariable("projectId") int projectId) {
        ProjectEntity project = projectService.viewProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("todo", "New Todo added Successfully!");
        return "addTodo";
    }
    
    @PostMapping("/addTodo/{projectId}")
    public String addTodo(@PathVariable("projectId") int projectId,Model model, @RequestParam("description") String description, TodoEntity todoEntity) {
        
    	ProjectEntity project = projectService.viewProjectById(projectId);
        
        todoEntity.setProjectEntity(project);
        
        todoService.addTodo(todoEntity);
        
    	model.addAttribute("todo", "New Todo added Successfully!");
        return "redirect:/viewDetails/" + projectId;
    }
    
    @GetMapping("/editTodo/{id}")
    public String editTodoPage(@PathVariable("id") int id, Model model) {
        TodoEntity todoEntity = todoService.getTodoById(id);
        model.addAttribute("todo", todoEntity);
        return "editTodo"; // Assuming the view name is editTodo.html
    }


    @PostMapping("/updateTodo")
    public String updateTodo(@ModelAttribute TodoEntity todoEntity, Model model) {
        
        TodoEntity existingTodo = todoService.getTodoById(todoEntity.getId());
       
        if (existingTodo != null && existingTodo.getProjectEntity() != null) {
       
            todoService.updateStatus(todoEntity.getId(), todoEntity);
            
            return "redirect:/viewDetails/" + existingTodo.getProjectEntity().getId();
        }
        else {
        	return "redirect:/";
        }
    }


    @GetMapping("/deleteTodo/{id}")
    public String deleteTodo(@PathVariable("id") String id) {
        int todoId = Integer.parseInt(id);
        TodoEntity todoEntity = todoService.getTodoById(todoId);
        if (todoEntity.getProjectEntity() != null) {
            int projectId = todoEntity.getProjectEntity().getId();
            todoService.deleteTodo(todoId);
            return "redirect:/viewDetails/" + projectId;
        } else {
           
            return "redirect:/"; 
        }
    }


}
