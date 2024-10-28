package accolade.test.historisation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.ObjectMapper;

import accolade.test.historisation.Entity.ActionType;
import accolade.test.historisation.Entity.Personnel;
import accolade.test.historisation.Service.ActionService;
import accolade.test.historisation.Service.PersonnelService;

@RequestMapping("/crud")
@Controller
public class PersonnelController {

	@Autowired
	private PersonnelService personnelService;

	@Autowired
	private ActionService actionService;

	@Autowired
    ObjectMapper objectMapper = new ObjectMapper(); 

	

	@RequestMapping(value = "/formulaire", method = RequestMethod.GET)
	public String inscription(Model model){
		Personnel personnel = new Personnel();
		model.addAttribute("personnel", personnel);
		return "formPersonnel";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPersonnelBis(@ModelAttribute Personnel personnel,Model model){
    	// RequestParam / RequestBody
		try {
            String personnelJson = objectMapper.writeValueAsString(personnel);
            System.out.println("Etape 1" + personnelJson);
            
        } catch (Exception e) {
			e.printStackTrace();
        }
		Personnel p1 = personnelService.savePersonnel(personnel);
		actionService.saveAction(p1, ActionType.Ajout);
		List<Personnel> personnels = listPersonnel(model);
		model.addAttribute("personnels", personnels);
		return "personnel";
	}

    

	@RequestMapping(value="delete/{id}", method = RequestMethod.GET)
	public String deletePersonnel(@PathVariable(value = "id") Long id, Model model){
		Personnel p1 = personnelService.getPersonnelById(id);
		try {
			personnelService.deletePersonnelById(id);
			actionService.saveAction(p1, ActionType.Suppression);
			
			List<Personnel> personnels = listPersonnel(model);
			model.addAttribute("personnels", personnels);
			return "personnel";
		} catch (Exception e) {
			 return "erreur";
		}
		
	}

	@RequestMapping(value="/read/{id}", method = RequestMethod.GET)
	public String getPersonnnelById(@PathVariable(value="id")Long id, Model model){
		try {
			Personnel personnel = personnelService.getPersonnelById(id);
			model.addAttribute("personnel", personnel);
			return "updatePersonnel";
		} catch (Exception e) {
			return "erreur";
		}
	}

	@RequestMapping(value="read", method = RequestMethod.GET)
	public String getAllPersonnnel(Model model){
		actionService.deleteActions();
		// List<Personnel> personnels = personnelRepository.findAll();
		List<Personnel> personnels = listPersonnel(model);
		model.addAttribute("personnels", personnels);
		return "personnel";
	}

	@RequestMapping(value="update/{id}", method = RequestMethod.POST)
	public String updatePersonnel(@ModelAttribute Personnel personnel, @PathVariable(value="id")Long id, Model model){
		try {
			String personnelJson = objectMapper.writeValueAsString(personnel);
			System.out.println("Etape 1" + personnelJson);
			Personnel p1 = personnelService.savePersonnel(personnel);
			actionService.saveAction(p1, ActionType.Mise_a_jour);
			List<Personnel> personnels = listPersonnel(model);
			model.addAttribute("personnels", personnels);
			return "personnel";
		} catch (Exception e) {
			// TODO: handle exception
			return "erreur";
		}	
	}

	public List<Personnel> listPersonnel(Model model){
		List<Personnel> personnels = personnelService.getAllPersonnel();
		return personnels;
	}
}
