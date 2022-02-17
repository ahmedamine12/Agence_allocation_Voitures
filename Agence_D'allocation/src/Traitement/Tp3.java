package Traitement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Tp3 {

	public static void main(String[] args) {
		Scanner sc;
		Agence agence = new Agence();
		try {
			agence = agence.LireSerialize();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			System.out.print(e1.getMessage());
		}
		sc = new Scanner(System.in);
		// Display menu graphics
		System.out.println(" ===========================================================");
		System.out.println("|                   MENU AGENCE                             |");
		System.out.println(" ===========================================================");
		System.out.println("| Options:                                                  |");
		System.out.println("|        1. AjouterVoiture                                  |");
		System.out.println("|        2. SupprimerVoiture                                |");
		System.out.println("|        3. Lister Voiture                                  |");
		System.out.println("|        4. Louer Voiture a un client                       |");
		System.out.println("|        5. Rend Voiture par un client                      |");
		System.out.println("|        6. Chercher voiture par criteres                   |");
		System.out.println("|        7. Lister Voiture louer avec leur client (Triee)   |");
		System.out.println("|        8. Exit                                            |");
		System.out.println(" ===========================================================");
		while (true) {
			System.out.print("\n\n***Veuillez selectionner une option :");
			while (!sc.hasNextInt()) {
				System.out.println("Int Invalide ! Entrer a nouveau : ");
				sc.next(); // this is important!
			}
			int str = sc.nextInt();
			sc.nextLine();
			switch (str) {
				case 1:
					System.out.println("\n Entrer les les info relatives a la voiture : ");
					Voiture temp = Voiture.SasirVoiture(agence.Nmb_Voiture());
					agence.AddVoiture(temp);
					try {
						agence.EcrireSerialize();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 2:
					agence.Imprimer_Voiture();
					System.out.print("\n\t Entrer l'id de la voiture a supprimer : ");
					while (!sc.hasNextInt()) {
						System.out.println("Int Invalide ! Entrer a nouveau : ");
						sc.next(); // this is important!
					}
					int id = sc.nextInt();
					agence.SupprimerVoiture(agence.find_voiture_byId(id));
					break;
				case 3:
					agence.Imprimer_Voiture();
					break;
				case 4:
					System.out.println("\n Entrer les les info relatives au client : ");
					Client temp2 = Client.SaisirClient();
					agence.Imprimer_Voiture();
					System.out.println("\n Choissisez l'une de ses voitures par Id : ");
					while (!sc.hasNextInt()) {
						System.out.println("Int Invalide ! Entrer a nouveau : ");
						sc.next(); // this is important!
					}
					id = sc.nextInt();
					Voiture v = agence.find_voiture_byId(id);
					agence.loueVoiture(temp2, v);
					try {
						agence.EcrireSerialize();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 5:
					System.out.println("\n Les voitures qui sont louer de l'agence");
					agence.Imprimer_TableHashage();

					System.out.println("\n Entrer l'Id de la voiture rendu par le client : ");
					while (!sc.hasNextInt()) {
						System.out.println("Int Invalide ! Entrer a nouveau : ");
						sc.next(); // this is important!
					}
					id = sc.nextInt();
					Voiture v1 = agence.find_voiture_byId(id);
					if (v1 != null)
						agence.rendVoiture(agence.ClientofVoiture(v1));
					else
						System.out.println("Veuillez bin verifier L'id de la Voiture :)");
					try {
						agence.EcrireSerialize();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 6: // suppression
					InterCritere My_Critere = new InterCritere();
					System.out.println("\n Pour chaque critere sp�cifier 0 ou 1 si vous voulez prendre en compte : \n");
					System.out.print("\n\t Pour Marque : ");
					while (!sc.hasNextInt()) {
						System.out.println("Int Invalide ! Entrer a nouveau : ");
						sc.next(); // this is important!
					}
					int choix = sc.nextInt();
					if (choix == 1) {
						sc.nextLine();
						System.out.print("\n\t\t Entrer la Marque :  ");
						String Marque = sc.nextLine();
						while (Marque.length() == 0) {
							System.out.println("Chaine vide ! Entrer a nouveau : ");
							Marque = sc.nextLine();
						}
						My_Critere.addCritere(new CritereMarque(Marque));
					}
					System.out.print("\n\t Pour Annee : ");
					while (!sc.hasNextInt()) {
						System.out.println("Int Invalide ! Entrer a nouveau : ");
						sc.next(); // this is important!
					}
					choix = sc.nextInt();
					if (choix == 1) {
						System.out.print("\n\t\t Entrer l'Annee :  ");
						while (!sc.hasNextInt()) {
							System.out.println("Int Invalide ! Entrer a nouveau : ");
							sc.next(); // this is important!
						}
						int Annee = sc.nextInt();
						My_Critere.addCritere(new CritereAnnee(Annee));
					}

					System.out.print("\n\t Pour Prix : ");
					while (!sc.hasNextInt()) {
						System.out.println("Int Invalide ! Entrer a nouveau : ");
						sc.next(); // this is important!
					}

					choix = sc.nextInt();
					if (choix == 1) {
						System.out.print("\n\t Entrer Prix :  ");
						while (!sc.hasNextInt()) {
							System.out.println("Int Invalide ! Entrer a nouveau : ");
							sc.next(); // this is important!
						}
						int Prix = sc.nextInt();
						My_Critere.addCritere(new CriterePrix(Prix));
					}
					Iterator<Voiture> ite = agence.selectionner(My_Critere);
					System.out.println(ite.hasNext());
					while (ite.hasNext()) {
						System.out.println(ite.next() + "\n");
					}
					try {
						agence.EcrireSerialize();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 7:
					agence.Imprimer_TableHashage();
					break;
				case 8:
					System.out.println("Exit selected");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid selection");
					break; // This break is not really necessary
			}
		}

	}
}