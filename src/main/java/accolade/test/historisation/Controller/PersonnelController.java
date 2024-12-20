package accolade.test.historisation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.ObjectMapper;

import accolade.test.historisation.entity.ActionType;
import accolade.test.historisation.entity.Personnel;
import accolade.test.historisation.service.IHistorisableActionAdaptater;
import accolade.test.historisation.service.IHistorisableService;
import accolade.test.historisation.service.PersonnelService;
import accolade.test.service.ActionService;
import accolade.test.service.IHistorisable;


@RequestMapping("/crud")
@Controller
public class PersonnelController {

	@Autowired
	private PersonnelService personnelService;

	@Autowired
	private ActionService actionService;

	@Autowired
	private IHistorisableService iHistorisableService;

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
		IHistorisable iHistorisable = new IHistorisableActionAdaptater(p1);
		actionService.saveCreateAction(p1.getId(),"Ajout d'un personnel", ActionType.Ajout.getId(), iHistorisable);
		List<Personnel> personnels = listPersonnel(model);
		model.addAttribute("personnels", personnels);
		return "personnel";
	}

    

	@RequestMapping(value="delete/{id}", method = RequestMethod.GET)
	public String deletePersonnel(@PathVariable(value = "id") Long id, Model model){
		
		Personnel p1 = personnelService.getPersonnelById(id);
		IHistorisable iHistorisable = new IHistorisableActionAdaptater(p1);
		try {
			personnelService.deletePersonnelById(id);
			actionService.saveDeleteAction(p1.getId(),"Suppression d'un personnel", ActionType.Suppression.getId(), iHistorisable);
			
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

			Personnel oldPersonnel = personnelService.getPersonnelById(id);
			Personnel updatePersonnel = personnel;

			System.out.println("DEBUT");
			System.out.println(oldPersonnel.toString() + " /// " + updatePersonnel.toString());
			String changes = iHistorisableService.getChanges(oldPersonnel, updatePersonnel);

			personnelService.savePersonnel(personnel);
			
			IHistorisable changesHistorisable = new IHistorisableActionAdaptater(changes);
			
			actionService.saveUpdateAction(updatePersonnel.getId(),"Mise à jour d'un personnel" ,ActionType.Mise_a_jour.getId(),changesHistorisable);
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
