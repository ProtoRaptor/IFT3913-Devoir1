import java.io.FileNotFoundException;

public class main {

	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		selecteurDeLecture a = new selecteurDeLecture();
		
		a.lecteur();
		//a.calculDC();
		System.out.println(a.classe_LOC);
		System.out.println(a.classe_CLOC);
		
		
	}
	
}
