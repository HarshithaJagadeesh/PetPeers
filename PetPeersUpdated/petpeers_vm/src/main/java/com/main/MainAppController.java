package com.main;

/**
  
  Case Study: Pet Peers 
  
  Technology: Spring MVC, Spring, JSP (HTML, CSS, JS etc.), Servlet Filter, Hibernate ORM, Log4j 2
  
  Description: The task is develop a 'Pet Peers'  a web based application, which provide features such as  storing & retrieving pet information, listing available pets and view details for a specific pet. It also allows a user to login/register, buy pets and view the pets owned by him. 
   
  The following are the User Stories for the Pet Peers application.
         1.    User Registration - New users can register themselves by entering a unique user name and password.
         2.    User Login - Users should be able to login into the application by entering a correct user name and password
         3.    View List of all Pets - This functionality lists all pets and also indicates whether a Pet is "Sold Out" or available for purchase. 
             If available, it provides a link to buy that Pet.
         4.    Add a Pet - A user can enter a new Pet to the list (anyone can buy this pet).
         5.    Buy a Pet - If a User likes an available Pet, she / he can buy the same by clicking on the "Buy" button.
         6.    List of owned Pets - A logged in user can view the list of Pets owned by him/her by clicking on "My Pets" button.
         7.    User Logout - A user can logout of the application by clicking on "Logout" button. After logout, user can access the application functionalities only after logging in again.
         8. Please show both success and failure message for each use case scenarios in the relevant pages. 
        
    You are provided with a partially implemented eclipse Maven web application project template, herewith you are expected to create the necessary objects and components in the given project template. 
    DB details are available in the PetConfig.java under "DAO" package.  

 

    Rules:
         1. During first time loading if there are errors, please clean the project and do update Maven Project with "Force Update of snapshot/Release" option
         
         2. Do not modify the Package, Class, its Methods name or signature
         
         3. Do not alter the request mapping URI and JSP names. 
         
         4. Follow Java coding conventions, best practices for better assessment feedback
         
         5. Write as many unit test cases in WebApplicationUnitTest.java for better test pass % and code coverage.
         
         6. Automatically check-in happens for every 30 minutes, for final submission refer Step 7a or 7b. 
         
         7. After completion of the use case development, please submit the code using one of below options
            a. Goto Git Staging window (in Eclipse -> Goto Window Menu -> Show View -> Others -> filter for Git Staging -> Click Open), add the necessary files to "Staged Changes" from "Unstaged Changes", enter the commit message 
               then click "Commit and Push", click "Preview", click "Push" and finally click "Close" button
                            or
            b. Goto Project Explorer (in Eclipse), right click on the UseCaseSubmission.bat and select open (Click Cancel if Editors available window pops up)        
         
         8. After code submission please allow 2 minutes for coding analysis process to complete.                 
    
    Deviating above rules will impact the assessment feedback.
          
    Quick Troubleshooting Tips:
        1. If there are errors during initial launch, please clean the project and do update Maven Project with "Force Update of snapshot/Release" option
        
        2. If you receive the Windows Defender Firewall windows couple of times initially, please click "Allow Access".  
        
        3. If you receive the DB error "You must configure either the server or JDBC driver", run the "SET GLOBAL time_zone = '+0:00'" in the MySQL Windows Client or MySQL Workbench (username: root, password: root)
        
        4. If you receive the timeout on launching(Run on Server) the project in Tomcat, please increase the timeout 
           (in Eclipse -> Goto Window Menu -> Show View -> Others -> Type "Server" in the filter and select "Servers" -> Click open -> Double click on "Tomcat 9.0 Server at localhost" -> Timeout section --> Increase the "Start(in seconds):" and "Stop(in seconds):" value.  )
        5. For the error message "We couldn't connect to the gateway because of an error. If this keeps happening, ask your admin or tech support for help", please refresh the page or goto the url https://rdweb.wvd.microsoft.com/webclient/index.html in the browser and relogin again. If still the error persists, please contact the SME/Trainer.

 

        6. For the error message "We couldn't connect because there are currently no available resources. Try again later or if this keeps happening, ask your admin or tech support for help.", please refresh the page or goto the url https://rdweb.wvd.microsoft.com/webclient/index.html in the browser and relogin again. If still the error persists, please contact the SME/Trainer.
        
        7. For the error message "We are working on refreshing your token, please try again after a short while or refresh your page.", please refresh the page or goto the url https://rdweb.wvd.microsoft.com/webclient/index.html in the browser and relogin again. If still the error persists, please contact the SME/Trainer.

 

 */

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.cs.model.Pet;
import com.hcl.cs.model.User;
import com.hcl.cs.service.PetPeersService;

@RestController
public class MainAppController {

	public MainAppController(PetPeersService petPeersService) {
		this.petPeersService = petPeersService;
	}

	@Autowired
	PetPeersService petPeersService;

	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("Index");
	}

	@GetMapping("/login")
	public ModelAndView login(@ModelAttribute("user") User user) {
		return new ModelAndView("LoginPage");
	}

	@PostMapping("/authenticateUser")
	public ModelAndView authenticateUser(HttpServletRequest req, @ModelAttribute("user") User user) {
		req.getSession().setAttribute("user", user);
		String name = req.getParameter("userName");
		String passwd = req.getParameter("userPasswd");
		User users = petPeersService.authenticateUser(name, passwd);
		if(users == null) {
			return new ModelAndView("InvalidUser");
		}else {
			req.getSession().setAttribute("id", users.getUserId());
			return new ModelAndView("HomePage", "pets", petPeersService.getAllPets());
		}
	}

	@GetMapping("/register")
	public ModelAndView register(@ModelAttribute("user") User user) {
		return new ModelAndView("Registration");
	}

	@PostMapping("/save")
	public ModelAndView saveUser(@ModelAttribute("user") User user) {
		User userCheck = petPeersService.saveUser(user);
		if(userCheck == null) {
			return new ModelAndView("Registration", "registered", "UserName already Taken!");
		}
		else
		{
			return new ModelAndView("LoginPage", "registered", "Registration Success");
		}	
	}

	@GetMapping("/home")
	public ModelAndView home(@ModelAttribute("user") User user) {

		return new ModelAndView("HomePage", "pets", petPeersService.getAllPets()); // replace with your solution
	}

	@GetMapping("/buyPet")
	public ModelAndView buyPet(HttpServletRequest req) {
		// chaged
		// int userid = (int) req.getSession().getAttribute("id");
		int userid = (Integer) req.getSession().getAttribute("id");

		int petId = Integer.parseInt(req.getParameter("petId"));
		req.getSession().setAttribute("petId", petId);

		System.out.println(req.getParameter("petId"));
		petPeersService.buyPet(petId, userid);
		return new ModelAndView("HomePage", "pets", petPeersService.getAllPets()); // replace with your solution
	}

	@GetMapping("/addPet")
	public ModelAndView addPet() {

		return new ModelAndView("AddPet", "pet", new Pet()); // replace with your solution
	}

	@PostMapping("/savePet")
	public ModelAndView savePet(@ModelAttribute("pets") Pet pet) {
		petPeersService.savePet(pet);
		return new ModelAndView("HomePage", "pets", petPeersService.getAllPets()); // replace with your solution
	}

	@GetMapping("/myPets")
	public ModelAndView petList(HttpServletRequest req) {
		// this changed
		// int userId = (int) req.getSession().getAttribute("id");
		int userId = (Integer) req.getSession().getAttribute("id");
		return new ModelAndView("Mypet", "mypets", petPeersService.getMyPets(userId));
		// replace with your solution
	}

	@GetMapping("/logout")
	public ModelAndView logout(@ModelAttribute("user") User user) {

		return new ModelAndView("LoginPage");
	}

}
