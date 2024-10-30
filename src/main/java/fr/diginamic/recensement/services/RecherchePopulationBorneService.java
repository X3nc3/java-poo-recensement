package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exception.ExceptionGlobal;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws ExceptionGlobal {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		if (!NumberUtils.isDigits(saisieMin) || !NumberUtils.isDigits(saisieMax)) {
			throw new ExceptionGlobal("Vous avez rentré une lettre au lieux d'un chiffre !");
		}

		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;

		if ( min<0 || max<0 || min>max ) {
			throw new ExceptionGlobal("Vous avez rentré un chiffre qui n'est pas dans le scope demandé !");
		}
		
		List<Ville> villes = rec.getVilles();

		for (Ville ville : villes) {
			boolean depTrouvé = false;
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				depTrouvé = true;
			}
			if (depTrouvé == true && ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
			} else {
				throw new ExceptionGlobal("Vous avez rentré un département inconnu !");
			}

		}
	}

}
