import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class selecteurDeLecture {

	
	public selecteurDeLecture() {}
	
	
	public void lecteur() throws FileNotFoundException {
		
		String testing = "test.txt";
		int nextEstComment = 0;
		
		Scanner lect = new Scanner(new File(testing));
		lect.useDelimiter("\n");
		while(lect.hasNext()){
			String ligne = lect.next();
			
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
	
	public int checkComment(String ligne) {
		
		if (ligne.contains("//")) {
			String[] data = ligne.split("//");
			System.out.print(data[0]);
			return 0;

		} else if(ligne.indexOf("/**") != -1) {
			int reste = 1;
			while(reste == 1) {
				int split = ligne.indexOf("/**");
				if(split != -1) {
					System.out.print(ligne.substring(0, split));
					int split2 = ligne.indexOf("*/");
					if(split2 != -1) {
						String lignePartielle = ligne.substring(split2+2);
						int splitPotentiel = lignePartielle.indexOf("/**");
						if(splitPotentiel != -1) {
							ligne = lignePartielle;
						} else {
							reste = 0;
							System.out.print(ligne.substring(split2+2));//probab modif ici
						}
					} else {
						
						return 1;
						
					}
				}
			}
		}
		
		if(ligne.indexOf("/*") != -1) {
			int reste = 1;
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
						}
						else {
							reste = 0;
							System.out.print(ligne.substring(split2+2));
						}
					} else {
						
						return 1;
						
					}
				}
			}
		} else {
			
			System.out.print(ligne);
			
		}
		
		return 0;
		
	}
	
}
