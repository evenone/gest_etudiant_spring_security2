/***************************************
 * Auteur : Lhoussaine IMOUGAR
 * Date   : 01/04/2017
 * Module :
 **************************************/

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
		
		/*Partie test de MAJ de la BD
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		etudiantRepository.save(new Etudiant("Alain",df.parse("1980-11-10"), "alain.gmail.com","alain.jpg"));
		etudiantRepository.save(new Etudiant("Eric",df.parse("1980-11-10"), "eric.gmail.com","eric.jpg"));
		etudiantRepository.save(new Etudiant("Bernard",df.parse("1980-11-10"), "bernard.gmail.com","bernard.jpg"));
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
