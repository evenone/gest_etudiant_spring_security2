package org.devup.web;

import java.io.File;
import java.io.FileInputStream;
import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.devup.dao.EtudiantRepository;
import org.devup.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.devup.entities.Pager;

@Controller
@RequestMapping(value="/")
public class EtudiantController {
	
	// les variables de paramétrage de la barre de pagination
	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Value ("${dir.images}")
	private String imageDir;
	
	@RequestMapping(value ="/",method = RequestMethod.GET)
	public String  pres(){
		return "presentation";
	}
	
	// Appel de la page Index avec la page et le nombre de ligne et le mots clé
	@RequestMapping(value ="/user/Index",method = RequestMethod.GET)
	public String  Index(Model model,
			@RequestParam(name="page", defaultValue="0",required = false) Integer p,
			@RequestParam(name="pageSize",required = false) Integer pageSize,
			@RequestParam(name="motCle",defaultValue="",required = false) String mc
			){

		int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
		// Gérer les cas d'exceptions de size ou de page à nul ou à 0 
		int evalPage = (p == 0 || p < 1) ? INITIAL_PAGE : p - 1;
		Page<Etudiant> pageEtudiants=etudiantRepository.chercherEtudiants("%"+mc+"%",new PageRequest(evalPage, evalPageSize));
		//Appel de pager pour calcul des plages à afficher
		Pager pager = new Pager(pageEtudiants.getTotalPages(), pageEtudiants.getNumber(), BUTTONS_TO_SHOW);
		
		int pagesCount=pageEtudiants.getTotalPages();
		int[] pages=new int[pagesCount];
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pageEtudiants",pageEtudiants);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motCle",mc);
		model.addAttribute("pagesCount",pagesCount);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pager",pager);
		
		System.out.println("-----le nombre evalPageSize "+evalPageSize+"--"+pageSize);
	
		return "etudiants";
	}
	//Appel du formulaire d'ajout
	@RequestMapping(value="/admin/form",method=RequestMethod.GET)
	public String formEtudiant(Model model){
		model.addAttribute("etudiant",new Etudiant());
		return "formEtudiant";
	}
	@RequestMapping(value="/admin/saveEtudiant",method=RequestMethod.POST)
	public String save(@Valid Etudiant et,
			BindingResult bindingResult,
			@RequestParam(name="picture") MultipartFile file
			) throws Exception {
		if (bindingResult.hasErrors()){
			return "formEtudiant";
		}
		
		if (!(file.isEmpty())){
			et.setPhoto(file.getOriginalFilename());
		}
		etudiantRepository.save(et);
		if (!(file.isEmpty())){
			et.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+et.getId()));
				
		}
		
		return "redirect:/user/Index";
	}
	@RequestMapping(value="/user/getPhoto",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody 	//envoie des donnée dans le corps de la req
	public byte[] getPhoto(Long id) throws Exception  {
		File f=new File(imageDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/admin/supprimer")
	public String supprimer(Long id){
		etudiantRepository.delete(id);
		return "redirect:/user/Index";
	}
	@RequestMapping(value="/admin/editer")
	public String editer(Long id,Model model){
		Etudiant et=etudiantRepository.getOne(id);
		model.addAttribute("etudiant",et);
		
		return "EditEtudiant";
	}
	@RequestMapping(value="/admin/UpdateEtudiant",method=RequestMethod.POST)
	public String update(@Valid Etudiant et,
			BindingResult bindingResult,
			@RequestParam(name="picture") MultipartFile file
			) throws Exception {
		if (bindingResult.hasErrors()){
			return "EditEtudiant";
		}
		if (!(file.isEmpty())){
			et.setPhoto(file.getOriginalFilename());
		}
		etudiantRepository.save(et);
		if (!(file.isEmpty())){
			et.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+et.getId()));
				
		}
		
		return "redirect:/user/Index";
	}
	@RequestMapping(value="/")
	public String home(){
		return "redirect:/user/Index";
	}
	@RequestMapping(value="/403")
	public String accessdenied(){
		return "403";
	}
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}
	@RequestMapping(value="/presentation")
	public String presentation(){
		return "presentation";
	}
	
}
