import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class selecteurDeLecture {

	int classe_LOC, paquet_LOC, classe_CLOC, lignesNCLOC, paquetCLOC, classe_DC, paquet_DC;
	
	public selecteurDeLecture() {
		
		classe_LOC = 0;
		paquet_LOC = 0;
		classe_CLOC = 0;
		lignesNCLOC = 0;
		paquetCLOC = 0;
		classe_DC = 0;
		paquet_DC = 0;
		
	}
	
	
	public int betterCheck(String ligneS) {
		
		
		
		return 0;
		
	}
	
	
	public void lecteur() throws FileNotFoundException {
		
		String testing = "test.txt";
		int nextEstComment = 0;
		
		Scanner lect = new Scanner(new File(testing));
		
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
				
				System.out.println(codeDansLigne);
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
	
}
