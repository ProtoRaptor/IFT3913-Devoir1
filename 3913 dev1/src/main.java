import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
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
		
		File classes = new File("classes.csv");
		File paquets = new File("paquets.csv");
		
		classes.createNewFile();
		paquets.createNewFile();
		String csv1 = "";
		String csv2= "";
		
		FileWriter csvFC = new FileWriter(classes);
		csv1 = csv1 + "chemin";
		csv1 = csv1 + ',';
		csv1 = csv1 + "class";
		csv1 = csv1 + ',';
		csv1 = csv1 + "classe_LOC";
		csv1 = csv1 + ',';
		csv1 = csv1 + "classe_CLOC";
		csv1 = csv1 + ',';
		csv1 = csv1 + "classe_DC";
		csv1 = csv1 + "\n";
		
		csvFC.write(csv1);
		
		FileWriter csvFP = new FileWriter(paquets);
		
		csv2 = csv2 + "chemin";
		csv2 = csv2 + ',';
		csv2 = csv2 + "class";
		csv2 = csv2 + ',';
		csv2 = csv2 + "classe_LOC";
		csv2 = csv2 + ',';
		csv2 = csv2 + "classe_CLOC";
		csv2 = csv2 + ',';
		csv2 = csv2 + "classe_DC";
		csv2 = csv2 + "\n";
		
		csvFP.write(csv2);
		
		String paquet = "";
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Path chemin = Paths.get(
					selecteur.getSelectedFile().getAbsolutePath());
					File comparePaq = new File(chemin.toString());
					File[] fichiersDansPaq = comparePaq.listFiles();
					Stream<Path> marche = Files.walk(chemin, 
					FileVisitOption.FOLLOW_LINKS).filter(
							p -> p.getFileName().toString().endsWith(".java"));
			marche.forEach(p ->{	
				try {
					if(paquetInfo.memePaquet.isEmpty()) {

						paquetInfo.mmPaq(p.getParent().getFileName().toString());
						}
					a.lecteur(p.toString());
					a.calculLOC();
					a.calculDC();
					a.calculWMC();
					String csv11="";
					csv11 = csv11 + p.toString();
					csv11 = csv11 + ',';
					csv11 = csv11 + p.getFileName()+"";
					csv11 = csv11 + ',';
					csv11 = csv11 + ""+a.classe_LOC;
					csv11 = csv11 + ',';
					csv11 = csv11 + ""+a.classe_CLOC;
					csv11 = csv11 + ',';
					csv11 = csv11 + ""+a.classe_DC;
					csv11 = csv11 + ',';
					csv11 = csv11 + ""+a.wMC;
					csv11 = csv11 + ',';
					csv11 = csv11 + ""+a.calculBC();
					csv11 = csv11 + "\n";
					csvFC.write(csv11);
					
					if(paquetInfo.memePaquet.equals(p.getParent().getFileName().toString())) {
						System.out.println(paquet.equals(p.getParent().getFileName().toString()));
						System.out.println();
						paquetInfo.LOC(a.classe_LOC);
						paquetInfo.CLOC(a.classe_CLOC);
						paquetInfo.WCP(a.wMC);
						
					} else{
						
						paquetInfo.DC();
						paquetInfo.BC();
						String csv22 = "";
						csv22 = csv22 + p.getParent().getFileName().toString();
						csv22 = csv22 + ',';
						csv22 = csv22 + p.getParent().getFileName().toString()+"";
						csv22 = csv22 + ',';
						csv22 = csv22 + ""+paquetInfo.paquet_LOC;
						csv22 = csv22 + ',';
						csv22 = csv22 + ""+paquetInfo.paquetCLOC;
						csv22 = csv22 + ',';
						csv22 = csv22 + ""+paquetInfo.paquet_DC;
						csv22 = csv22 + ',';
						csv22 = csv22 + ""+paquetInfo.paquetWCP;
						csv22 = csv22 + ',';
						csv22 = csv22 + ""+paquetInfo.paquetBC;
						csv22 = csv22 + "\n";
						csvFP.write(csv22);
						paquetInfo.reset();
						paquet.replace(paquet, p.getParent().getFileName().toString());
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			});
			if(paquetInfo.paquet_LOC > 0) {
				paquetInfo.DC();
			paquetInfo.BC();
			String csv22 = "";
			csv22 = csv22 + new File(chemin.getFileName().toString());
			csv22 = csv22 + ',';
			csv22 = csv22 + new File(chemin.getFileName().toString());
			csv22 = csv22 + ',';
			csv22 = csv22 + ""+paquetInfo.paquet_LOC;
			csv22 = csv22 + ',';
			csv22 = csv22 + ""+paquetInfo.paquetCLOC;
			csv22 = csv22 + ',';
			csv22 = csv22 + ""+paquetInfo.paquet_DC;
			csv22 = csv22 + ',';
			csv22 = csv22 + ""+paquetInfo.paquetWCP;
			csv22 = csv22 + ',';
			csv22 = csv22 + ""+paquetInfo.paquetBC;
			csv22 = csv22 + "\n";
			csvFP.write(csv22);
			}
			csvFC.flush();
			csvFC.close();
			csvFP.flush();
			csvFP.close();
		}
		
		
		
	}
	
}
