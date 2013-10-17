
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Alojamiento {

	private String Etiqueta;
	private String Instruccion;
	private String Operando;
	private String Dirr;
	private String Local;
	private String Acu;
	private String Conb;
	
	//Etiqueta, Instr, Oper, Dirr, Acuu, CLocal, Conb 
	public Alojamiento (String Etiqueta, String Instruccion,String Operando, String Dirr, String Acu, String Local, String Conb){
		super();
		this.Etiqueta = Etiqueta;
		this.Instruccion = Instruccion;
		this.Operando = Operando;
		this.Dirr = Dirr;
		this.Local = Local;
		this.Acu = Acu;
		this.Conb = Conb;
	}
	
	public String getEtiqueta() {
		return Etiqueta;
	}
	public void setEtiqueta(String Etiqueta) {
		this.Etiqueta = Etiqueta;
	}
	public String getConb() {
		return Conb;
	}
	public void setConb(String Conb) {
		this.Conb = Conb;
	}
	public String getInstruccion() {
		return Instruccion;
	}
	public void setInstruccion(String Instruccion) {
		this.Instruccion = Instruccion;
	}
	public String getOperando() {
		return Operando;
	}
	public void setOperando(String Operando) {
		this.Operando = Operando;
	}
	public String getDirr() {
		return Dirr;
	}
	public void setDir(String Dirr) {
		this.Dirr = Dirr;
	}
	public String getLocal() {
		return Local;
	}
	public void setLocal(String Local) {
		this.Dirr = Local;
	}
	public String getAcu() {
		return Acu;
	}
	public void setAcu(String Acu) {
		this.Dirr = Acu;
	}

	String imprimirVector (int nlinea) {
		String Etiquetav = this.getEtiqueta();
		String Instruccionv = this.getInstruccion();
		String topoperando= this.getOperando();
		String Acu = this.getAcu();
		String gdirr= this.getDirr();
		String gcloca = this.getLocal();
		String gcConb = this.getConb();
		
		String ardillacu= null;
		int countoperando = 0;
		
		
		System.out.println ("Etiqueta: " + Etiquetav);
		System.out.println ("Instruccion: " + Instruccionv);
		System.out.println ("Operando(s): " +topoperando);
		
		System.out.println ("Numero de Operandos: " + countoperando);
		System.out.println ("Direccionamiento: " + gdirr);
		System.out.println ("Contador Localidad : " + gcloca);
		System.out.println ("Contador Bytes : " + gcConb);
		
		
		if(topoperando.length()!=0 ){
			char opi[]=null;
			opi = topoperando.toCharArray();
			
			//Etiqueta + Abrir TBS
			if(opi[0]>='A' && opi[0]<='Z'){
				String operx = BusquedaTBS(topoperando);
				System.out.println ("Valor ETQ : " + operx);
				Acu = wracu (gcConb, Acu, operx);
				
			}
			else{
			//Analizar Operando
				if(gdirr.compareTo("EXT")==0 || gdirr.compareTo("IMM")==0 || gdirr.compareTo("DIR")==0 ){
					String opera = AnalizaOP(topoperando);
					while((opera.length()%2)==1){
						opera="0"+opera;
					}
					System.out.println("OPERA "+opera);
					Acu = wracu (gcConb, Acu, opera);
				}
				
			}
		
		}
		System.out.println ("Acu : " + Acu);
		
		
		ardillacu=gcloca+"\t\t\t"+Acu+"\t\t\t"+Etiquetav+"\t\t\t"+Instruccionv+"\t\t\t"+topoperando;
		if(Instruccionv.compareTo("")==0){
			return "";
		}
		else{
			return ardillacu;
		}
	}
	
	String BusquedaTBS (String Etiquetav){
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
		String Etiquet = null;
		String Direccion = null;
		String operx = "";
		try 
		{
			archivo = new File ("Prueba.tbs");
	        fr = new FileReader (archivo);
	        br = new BufferedReader(fr);
	        String linea;
	        while((linea=br.readLine())!=null){
	        	
	        	StringTokenizer instrsplit = new StringTokenizer(linea);
	        	Etiquet = instrsplit.nextToken();
	        	Direccion = instrsplit.nextToken();
	        		
	        	if (Etiquetav.compareTo(Etiquet)==0 ){
	        		operx = Direccion;
	        		break;
	        	}
	        	else {
	        		operx = "";
	        	}
	        }
		}catch (Exception ie)
		{
			ie.printStackTrace();
		}
		return operx;
		
	}
	
	String escribir(){
		String Etiquetav = this.getEtiqueta();
		String gcloca = this.getLocal();
		String ardillacu= "";
		
		if(Etiquetav.compareTo("")==0){
			
		}
		else{
			ardillacu=Etiquetav+"\t\t\t"+gcloca+"\n";
		}
		
		return ardillacu;
		
	}
	
	String wracu (String gcConb,String Acu, String operx){
		StringTokenizer acucut = new StringTokenizer(Acu);
		int cacu= acucut.countTokens();
		String a1 = "";
		String a2 = "";
		String a3 = "";
		String a4 = "";
		
		
		if(cacu == 4 && gcConb.compareTo("4")==0){
			//Escribimos 3
			a1= acucut.nextToken();
			a2= acucut.nextToken();
			a3= acucut.nextToken();
			a4= acucut.nextToken();
			
			a2=operx.substring(0,2);
			a3=operx.substring(2,4);
			a4=operx.substring(5,6);
			Acu = a1+" "+a2+" "+a3+" "+a4;
			
		}
		if(cacu == 3 && gcConb.compareTo("3")==0){
			//Escribimos 2
			a1= acucut.nextToken();
			a2= acucut.nextToken();
			a3= acucut.nextToken();

			a2=operx.substring(0,2);
			a3=operx.substring(2,4);
			Acu = a1+" "+a2+" "+a3;
			
		}
		if(cacu == 2 && gcConb.compareTo("2")==0){
			//Escribimos 1
			a1= acucut.nextToken();
			a2= acucut.nextToken();
			a2=operx.substring(0,2);
			
			Acu = a1+" "+a2;
		}
		return Acu;
		
	}
	
	String AnalizaOP (String opera){
		char opi[];
		StringTokenizer opersin = new StringTokenizer(opera,"#$@%");
		int opp = 0;
		String Val = "";
		opi = opera.toCharArray();
		
		
			if (opi[0]=='#'){
				//IMM
				if (opi[1]=='$'){
					Val = opersin.nextToken();
					opera = Val;
				}
				else if (opi[1]=='@'){
					//OCTA
					Val  = opersin.nextToken();
					opp = Integer.parseInt(Val,8);
					opera =  Integer.toString(opp, 16);
				}
				else if (opi[1]=='%'){
					//BIN
					Val = opersin.nextToken();
					//System.out.println(Acu);
					opp = Integer.parseInt(Val,2);
					opera =  Integer.toString(opp, 16);
				}
				else if (opi[1]>='0' || opi[1]<='9')
				{
					
					Val = opersin.nextToken();
					opp = Integer.parseInt(Val,10);
					opera =  Integer.toString(opp, 16);
				}
				
			}
			else if (opi[0]=='$'){
				Val = opersin.nextToken();
				opera = Val;
			}
			else if (opi[0]=='@'){
				//OCTA
				Val  = opersin.nextToken();
				opp = Integer.parseInt(Val,8);
				opera =  Integer.toString(opp, 16);
			}
			else if (opi[0]=='%'){
				//BIN
				Val = opersin.nextToken();
				//System.out.println(Acu);
				opp = Integer.parseInt(Val,2);
				opera =  Integer.toString(opp, 16);
			}
			else if (opi[0]>='0' || opi[0]<='9')
			{
				Val = opersin.nextToken();
				opp = Integer.parseInt(Val,10);
				opera =  Integer.toString(opp, 16);
			}
			
		return opera;
		
	}
	
}

