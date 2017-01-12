package org.devup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.devup.dao.EtudiantRepository;
import org.devup.entities.Etudiant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class TpSpringMvcApplication {

	public static void main(String[] args) throws ParseException  {
		
		ApplicationContext ctx= SpringApplication.run(TpSpringMvcApplication.class, args);
		EtudiantRepository etudiantRepository =ctx.getBean(EtudiantRepository.class);
		/*Inserer un jeu d'essai dans la base et tester la maj
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		etudiantRepository.save(new Etudiant("Ahmed",df.parse("1980-11-10"), "Ahmed.gmail.com","Ahmed.jpg"));
		etudiantRepository.save(new Etudiant("Mohamed",df.parse("1980-11-10"), "Mohamed.gmail.com","Mohamed.jpg"));
		etudiantRepository.save(new Etudiant("Ibrahim",df.parse("1980-11-10"), "Inrahim.gmail.com","Ibrahim.jpg"));
		System.out.println("--------------------------------------------------------------------------------- ");
		System.out.println("Liste des etudiants...... ");
		System.out.println("--------------------------------------------------------------------------------- ");
		/*Page<Etudiant> etds=etudiantRepository.findAll(new PageRequest(0,5));
		Page<Etudiant> etds=etudiantRepository.chercherEtudiants("%B%",new PageRequest(0,5));
		etds.forEach(e->System.out.println(e.getNom())); 
		*/
		
		System.out.println("Fin initialisation...."); 
		
		
	}
}
