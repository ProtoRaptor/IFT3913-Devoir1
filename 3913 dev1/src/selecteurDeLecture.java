import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class selecteurDeLecture {

	int classe_LOC, paquet_LOC, classe_CLOC, lignesNCLOC, 
		paquetCLOC, classe_DC, paquet_DC, nbDeFonction,
		wMC, noeudPredicat;
	//liste qui conserve le nombre de predicat par fonction detecter
	ArrayList<Integer> fonctions;
	
	public selecteurDeLecture() {
		
		classe_LOC = 0;
		paquet_LOC = 0;
		classe_CLOC = 0;
		lignesNCLOC = 0;
		paquetCLOC = 0;
		classe_DC = 0;
		paquet_DC = 0;
		nbDeFonction = 0;
		wMC = 0;
		noeudPredicat = 0;
		fonctions = new ArrayList<Integer>();
		
	}
	
	
	public int betterCheck(String ligneS) {
		
		
		
		return 0;
		
	}
	
	
	public void lecteur(String input) throws FileNotFoundException {
		
		int nextEstComment = 0;
		int dansClass = 0;
		//determine le nombre d'operations imbriquer de la position actuelle
		//ex: void h(){ if(){} } = 2
		int profondeurDansClass = 0;
		//conserve le nom de la fonction en cours d'analyse au besoin
		String fonctionCourante = "";
		
		Scanner lect = new Scanner(new File(input));
		
		//Il est trivial de verifier /** car si on trouve /*
		//on trouve egalement /**, vu que la distinction est arbitraire
		//dans le contexte de ce TP, tout deux comptent comme comment
		
		while(lect.hasNext()){
			String ligne = lect.nextLine();
			String codeDansLigne = "";
			//determine si une ligne a deja ete evaluer.
			int compter = 0;
			
			if(ligne.isBlank() == false) {
				
				int estComment = nextEstComment;
				
				for(int i = 0; i<ligne.length(); i++) {
					
					switch(ligne.charAt(i)) {
					//determine debut d'un comment/javadoc
						case '/':
							if(ligne.charAt(i+1) == '/') {
								if(estComment == 0 && compter == 0) {
									classe_CLOC++;
									nextEstComment = 0;
									i = ligne.length();
									break;
								}
							}
							
							if(ligne.charAt(i+1) == '*') {
								estComment = 1;
								if(compter == 0) {
									classe_CLOC++;
									compter = 1;
								}
								i++;
							}
							break;
							
					//determine la fin d'un comment/javadoc	
						case '*':
							if(estComment == 1 && ligne.charAt(i+1) == '/') {
								estComment = 0;
								i++;
							}
							break;
							
					//determine les characteres hors commentaire donc le code
						default:
							if(estComment == 0) {
								codeDansLigne =codeDansLigne + ligne.charAt(i);
							}
					}
					
				}
				
				if(codeDansLigne.isBlank() == false) {
					lignesNCLOC++;
				}
				
				//check au cas ou on a rater un comment, probab facultatif
				if(estComment == 1) {
					if(compter == 0) {
						classe_CLOC++;
					}
					nextEstComment = 1;
				}
				
				//on considere les 
				int finit = 0;
				String lignePart = codeDansLigne;
				while(finit == 0) {
					if(lignePart.indexOf("if") != -1) {
						
						noeudPredicat++;
						lignePart = lignePart.substring(lignePart.indexOf("if")+2);
						
					} else if(lignePart.indexOf("for") != -1){
						
						noeudPredicat++;
						lignePart = lignePart.substring(lignePart.indexOf("for")+3);
							
					} else if(lignePart.indexOf("while") != -1) {
						
						noeudPredicat++;
						lignePart = lignePart.substring(lignePart.indexOf("while")+5);
						
					} else if(lignePart.indexOf("switch") != -1) {
						
						noeudPredicat++;
						lignePart = lignePart.substring(lignePart.indexOf("switch")+6);
						
					} else {
						
						finit = 1;
						
					}
				}
			
			
			}
				
		}
		
	}
	public void calculLOC() {
		
		classe_LOC = classe_CLOC + lignesNCLOC;
		
	}
	
	public void calculDC(){
		calculLOC();
		if(classe_LOC > 0)
			classe_DC = classe_CLOC / classe_LOC ;
		
	}
	
	public void calculWMC() {
		
		wMC = 1 + noeudPredicat;
		
	}
	
	public int calculBC() {
		
		return classe_DC/wMC;
		
	}
	
}
