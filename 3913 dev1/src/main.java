import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class main {

	
	public static void main(String[] args) throws IOException {
		
		JPanel source = new JPanel();
		JFileChooser selecteur = new JFileChooser();
		selecteur.setCurrentDirectory(new File(System.getProperty("user.dir")));
		selecteur.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = selecteur.showOpenDialog(source);
		selecteurDeLecture a = new selecteurDeLecture();
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Path chemin = Paths.get(
					selecteur.getSelectedFile().getAbsolutePath());
					Stream<Path> marche = Files.walk(chemin, 
					FileVisitOption.FOLLOW_LINKS).filter(
							p -> p.getFileName().toString().endsWith(".java"));
			marche.forEach(p ->{	
				try {
					a.lecteur(p.toString());
					a.calculLOC();
					a.calculDC();
					a.calculWMC();
					a.calculBC();
					
					FileWriter csv = new FileWriter("classes.csv");
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			});
		}
		
		
		
	}
	
}
