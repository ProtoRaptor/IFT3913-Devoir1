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
	
	
	public void lecteur() throws FileNotFoundException {
		
		String testing = "test.txt";
		int nextEstComment = 0;
		
		Scanner lect = new Scanner(new File(testing));
		lect.useDelimiter("\n");
		while(lect.hasNext()){
			String ligne = lect.next();
			
			if(ligne.isBlank() == false) {
				if(nextEstComment == 1) {
				
					int endDeComment = ligne.indexOf("*/");
					if(endDeComment != -1) {
						ligne = ligne.substring(endDeComment+2);
						nextEstComment = 0;
						checkComment(ligne);
					}
					
				} else {
				
					nextEstComment = checkComment(ligne);
				
				}
			
			}
				
		}
		
	}
	//Il est non necessaire de verifier /** car si on trouve /*
	//on trouve egalement /**, vu que la distinction est arbitraire
	//dans le contexte de ce TP, tout deux compte comme comment
	public int checkComment(String ligne) {
		//determine si une ligne a deja ete evaluer.
		int compter = 0;
		//sert a determiner si la ligne ferme le commentaire
		int reste = 1;
		
		if (ligne.indexOf("//") != -1) {
			String ligneGauche = ligne.substring(0, ligne.indexOf("//"));
			classe_CLOC++;
			//detecte du code avant un commentaire '//' sur une meme ligne
			if(ligneGauche.isBlank() == false) {
				if(ligneGauche.indexOf("/**") == -1 
						&& ligneGauche.indexOf("/*") == -1) {
					lignesNCLOC++;
					System.out.print(ligneGauche);
				} else {
					int finComment = ligneGauche.indexOf("*/");
					if(finComment != -1 && finComment > ligneGauche.indexOf("/*")) {
						String coupe = ligneGauche.substring(ligne.indexOf("*/")+2);
						if(coupe.indexOf("/**") != -1 || coupe.indexOf("/*") != -1) {
							if(coupe.substring(0, coupe.indexOf("/*")).isBlank() == false 
									&& compter == 0) {
								
								lignesNCLOC++;
								compter = 1;
								
							}
						} else {
							
							reste = 0;
							
						}
					} else if(ligne.substring(ligne.indexOf("//")).indexOf("*/") != -1){
						
						return 1;
						
					}
					
				}
			}
			return 0;

		} else if(ligne.indexOf("/**") != -1) {
			classe_CLOC++;
			 reste = 1;
			while(reste == 1) {
				int split = ligne.indexOf("/**");
				if(split != -1) {
					if(ligne.substring(0, split).isBlank() == false 
							&& compter == 0) {
						
						lignesNCLOC++;
						compter = 1;
						
					}
					System.out.print(ligne.substring(0, split));
					int split2 = ligne.indexOf("*/");
					if(split2 != -1) {
						String lignePartielle = ligne.substring(split2+2);
						int splitPotentiel = lignePartielle.indexOf("/**");
						if(splitPotentiel != -1) {
							ligne = lignePartielle;
							//detecte du code dans une ligne entre deux commentaire/javadoc
							if(ligne.substring(0, splitPotentiel).isBlank() == false && compter == 0) {
								lignesNCLOC++;
								compter = 1;
							}
								
						} else {
							reste = 0;
							//detecte du code a la suite d'un commentaire/javadoc sur une meme ligne
							if(lignePartielle.isBlank() == false && compter == 0) {
								lignesNCLOC++;
								System.out.print(ligne.substring(split2+2));//probab modif ici
							}
						}
					} else {
						//signifie que la prochaine ligne sera dans un commentaire /* ou /**
						return 1;
						
					}
				}
			}
		}
		
		if(ligne.indexOf("/*") != -1) {
			 reste = 1;
			while(reste == 1) {
				int split = ligne.indexOf("/*");
				if(split != -1) {
					System.out.print(ligne.substring(0, split));
					int split2 = ligne.indexOf("*/");
					if(split2 != -1) {
						String lignePartielle = ligne.substring(split2+2);
						int splitPotentiel = lignePartielle.indexOf("/*");
						if(splitPotentiel != -1) {
							ligne = lignePartielle;
							//detecte du code dans une ligne entre deux commentaire/javadoc
							if(ligne.substring(0, splitPotentiel).isBlank() == false && compter == 0) {
								classe_CLOC++;
								lignesNCLOC++;
								compter = 1;
							}
						}
						else {
							reste = 0;
							//detecte du code a la suite d'un commentaire/javadoc sur une meme ligne
							if(lignePartielle.isBlank() == false) {
								classe_CLOC++;
								lignesNCLOC++;
								System.out.print(ligne.substring(split2+2));//probab modif ici
							}
						}
					} else {
						//signifie que la prochaine ligne sera dans un commentaire /* ou /**
						return 1;
						
					}
				}
			}
		} else {
			//Si sa arrive ici c'est juste du code
			lignesNCLOC++;
			System.out.print(ligne);
			
		}
		
		return 0;
		
	}
	
	public void calculDC(){
		
		classe_DC = classe_CLOC / classe_LOC ;
		
	}
	
	public void calculLOC() {
		
		classe_LOC = classe_CLOC + lignesNCLOC;
		
	}
}
