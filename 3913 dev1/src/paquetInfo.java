
public class paquetInfo {
	
	public static int paquet_LOC =0, paquetCLOC =0, paquet_DC=0, paquetWCP=0,
	paquetBC=0;
	public static String memePaquet = "";
	
	public paquetInfo() {}
	

	public static void LOC(int a) {
		
		paquet_LOC = paquet_LOC + a;
		
	}
	
	public static void CLOC(int a) {
		
		paquetCLOC = paquetCLOC + a;
		
	}
	
	public static void DC() {
		
		if(paquet_LOC > 0)
			paquet_DC = paquetCLOC/paquet_LOC;
		
	}
	
	public static void WCP(int a) {
		
		paquetWCP = paquetWCP + a;
		
	}
	
	public static void BC() {
		if(paquetWCP>0)
			paquetBC = paquet_DC/paquetWCP;
		
	}
	
	public static void mmPaq(String a) {
		
		memePaquet = a;
		
	}
	
	public static void reset() {
		
		paquet_LOC =0;
		paquetCLOC =0;
		paquet_DC=0;
		paquetWCP=0;
		paquetBC=0;
		memePaquet="";
		
	}
	
}
