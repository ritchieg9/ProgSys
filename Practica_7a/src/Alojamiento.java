
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Alojamiento {

	private String Etiqueta;
	private String Instruccion;
	private String Operando;
	private String Topoperandor;
	private String Dirr;
	private String Local;
	private String Localr;
	private String Acu;
	private String Conb;
	private String Instruccionr;
	
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
		
		Localr=gcloca;
		Topoperandor = topoperando;
		Instruccionr = Instruccionv;
		
		if(topoperando.length()!=0 ){
			char opi[]=null;
			opi = topoperando.toCharArray();
			
			//Etiqueta + Abrir TBS
			if(opi[0]>='A' && opi[0]<='Z'){
				String operx = BusquedaTBS(topoperando);
				System.out.println ("Valor ETQ : " + operx);
				Acu = wracu (gcConb, Acu, operx, gdirr);
				
			}
			else{
			//Analizar Operando
				if(gdirr.compareTo("EXT")==0 || gdirr.compareTo("IMM")==0 || gdirr.compareTo("DIR")==0 || gdirr.compareTo("REL")==0){
					String opera = AnalizaOP(topoperando);
					while((opera.length()%2)==1){
						opera="0"+opera;
					}
					Acu = wracu (gcConb, Acu, opera, gdirr);
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
	
	String wracu (String gcConb,String Acu, String operx, String gdirr){
		StringTokenizer acucut = new StringTokenizer(Acu);
		int cacu= acucut.countTokens();
		String a1 = "";
		String a2 = "";
		String a3 = "";
		String a4 = "";
		
		if(gdirr.compareTo("REL")==0){
			char opi[];
			String rr="";
			opi = Acu.toCharArray();
			System.out.println("REEEEEEEEL");
			for(int i=0; i<Acu.length(); i++ ){
				if(opi[i]=='r'){
					rr = tratarel(Instruccionr, Acu,Localr, Topoperandor, 1 );
					
					a1= acucut.nextToken();
					a2= acucut.nextToken();
					a2= rr;
					
					Acu = a1+" "+a2;
					break;
				}
				if(opi[i]=='q'){
					rr = tratarel(Instruccionr, Acu,Localr, Topoperandor, 2 );
					
					a1= acucut.nextToken();
					a2= acucut.nextToken();
					a3= acucut.nextToken();
					a3= rr;
					
					Acu = a1+" "+a2+" "+a3;
					
					break;
				}
				if(opi[i]=='l'){
					Acu = tratarel(Instruccionr, Acu,Localr, Topoperandor, 3 );
					
					a1= acucut.nextToken();
					a2 = " "+Acu;
					
					Acu = a1+a2;
					
					break;
				}
			}
		}
		else {
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
				
				if(operx.length()<3){
					a2="00";
					a3= operx.substring(0,2);
				}
				else{
				a2=operx.substring(0,2);
				a3=operx.substring(2,4);
				}
				Acu = a1+" "+a2+" "+a3;
				
			}
			if(cacu == 2 && gcConb.compareTo("2")==0){
				//Escribimos 1
				a1= acucut.nextToken();
				a2= acucut.nextToken();
				a2=operx.substring(0,2);
				
				Acu = a1+" "+a2;
			}
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
					opp = Integer.parseInt(opera,16);
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
				opp = Integer.parseInt(opera,16);
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
		Topoperandor = opera;	
		return opera;
		
	}
	
	public String tratarel(String Instruccionr, String gdir, String CONTL, String Topoperandor, int tipo){
		//System.out.println("INS: "+Instruccionr+" GDIR: "+gdir+" CONTL: "+CONTL+" Topo: "+Topoperandor);
		String xx = "";
		String zz = "";
		String reg = "";
		String ist = "";
		String c3 = "";
		int rg= 0;
		int it= 0;
		StringTokenizer lb = new StringTokenizer (Topoperandor," ,");
		int yy = 0;
		int cc = Integer.parseInt(CONTL, 16);
		int rr = 0;
		int x= 0;
		int y=0;
		int nlb=0;
		char charval[];
		charval = xx.toCharArray();
		
		switch(tipo){
		case 1:
			System.out.println("Tipo rr");
			System.out.println("Topo: "+Topoperandor);
			yy = Integer.parseInt(Topoperandor, 16);
			rr = yy - (cc + 2);
			xx = Integer.toHexString(rr);
			System.out.println("INT: "+rr);
			System.out.println("RR: "+xx);
			
			
			x = xx.length();
			xx = xx.substring(x-2,x);
			
			break;
		case 2:
			System.out.println("Tipo qq rr");
			System.out.println("Topo: "+Topoperandor);
			yy = Integer.parseInt(Topoperandor, 16);
			rr = yy - (cc + 4);
			xx = Integer.toHexString(rr);
			System.out.println("INT: "+rr);
			System.out.println("RR: "+xx);
			
			x = xx.length();
			xx = xx.substring(x-4,x);
			zz = xx.substring(2,4);
			xx = xx.substring(0,2)+" "+zz;
			
			break;
		case 3:
			System.out.println("Tipo lb rr");
			
			ist = lb.nextToken();
			xx = lb.nextToken();
			zz = xx;
			
			if(ist.compareTo("A")==0){
				rg = 0;
			}
			else if(ist.compareTo("B")==0){
				rg = 1;
			}
			else if(ist.compareTo("D")==0){
				rg = 4;
			}
			else if(ist.compareTo("X")==0){
				rg = 5;
			}
			else if(ist.compareTo("Y")==0){
				rg = 6;
			}
			else if(ist.compareTo("SP")==0){
				rg = 7;
			}

			if(Instruccionr.compareTo("DBEQ")==0){
				it = 0;
			}
			else if(Instruccionr.compareTo("DBNE")==0){
				it = 32;
			}
			else if(Instruccionr.compareTo("TBEQ")==0){
				it = 64;
			}
			else if(Instruccionr.compareTo("TBNE")==0){
				it = 96;
			}
			else if(Instruccionr.compareTo("IBEQ")==0){
				it = 128;
			}
			else if(Instruccionr.compareTo("IBNE")==0){
				it = 160;
			}
			System.out.println("Instruccion: "+it);
			yy = Integer.parseInt(xx);
			System.out.println("Valor Despues del ,: "+yy);
			cc+=4;
			if(yy > cc){
				//NEGATIVO
				nlb = it + rg;
				xx = Integer.toHexString(nlb);
				System.out.println("NLB: "+xx);
				yy = Integer.parseInt(zz, 10);
				System.out.println("YY: "+yy);
				rr = yy - cc;
				c3 = Integer.toHexString(rr);
				System.out.println("RR: "+c3);
				y=c3.length();
				zz = c3.substring(y-2,y);
				xx = xx+" "+zz;
				
				System.out.println("FIN: "+xx);
				
			}
			else{
				//POSITIVO
				nlb = it + rg+16;
				xx = Integer.toHexString(nlb);
				System.out.println("PLB: "+xx);
				System.out.println("CC: "+cc);
				yy = Integer.parseInt(zz, 10);
				System.out.println("YY: "+yy);
				rr = yy - cc;
				c3 = Integer.toHexString(rr);
				System.out.println("RR: "+c3);
				y=c3.length();
				zz = c3.substring(y-2,y);
				xx = xx+" "+zz;
				
				System.out.println("FIN: "+xx);
			}
			break;
		default:
			break;
		}
		return xx;
	}
	
}

