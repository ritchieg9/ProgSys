//Cadenatxt
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;


public class Cadenatxt {
	Vector <Alojamiento> lista = new Vector <Alojamiento> ();
	
	String Etiqueta= null;
	String Instr= null;
	String Oper= null;
	String Nul= null;
	String Dirr = null;
	String Acu=null;
	String LOCAN = "0000";
	String LOCA = "0000";
	String operi = "";
	String xopero = "";
	int xrel = 1;
	int Clocd = 0;
	String contadorb = "";
	String CL = "0";
	String CN = "0";
	int cl = 0;
	int cln = 0;
	int scl = 0;
	
	String DeleteComment (String cutxt){
		
		//System.out.println(cutxt);
		char charval[];
		charval = cutxt.toCharArray();
		String subusado = "";
		int x=0;
		for(x=1; x < cutxt.length(); x++ ){		
			if(charval[x]==';'){
				subusado = cutxt.substring(0,x);
				x=cutxt.length();
			}	
		}
		if (x==cutxt.length()){
			subusado = cutxt;
		}
		return subusado;
	}
	
	public String[] Cortador(String cadena, String CAL){
		char charval[];
		char codver[];
		charval = cadena.toCharArray();
		StringTokenizer tokens = new StringTokenizer(cadena);
		int cuentatokens = tokens.countTokens();
		String Typex = null;
		
		String ORG = "ORG";
		String EQU = "EQU";
		String END = "END";
		
		if (cuentatokens==3){
			Etiqueta = tokens.nextToken();
			codver = Etiqueta.toCharArray();
			if(Etiqueta.length() > 8 || codver[0] < 'A' || codver[0] > 'z' ){
				Etiqueta = ""; //Etiqueta con error
			}
			Instr = tokens.nextToken();
			Oper =  tokens.nextToken();
			//System.out.println("\n\n3 Tokens Instruccion: "+ Instr);
			if (Instr.compareTo(ORG)!=0 && Instr.compareTo(EQU)!=0 && Instr.compareTo(END)!=0){ 
				Typex = AnaOper (Oper);
				String B = Busqueda (Instr, Typex);
				if (B.compareTo(Typex)==0){
					//System.out.println("El Dir Acu T Obtenido = "+Typex);
					Dirr = Typex;
				}
				else {
					//System.out.println("El Dir Acu B Obtenido = "+B);
					Dirr = B;
				}
			}
			else if (Instr.compareTo(ORG)==0){
				Dirr = " ";
				operi = "";
				cl = AnaOperColoc (Oper);
				CL = Integer.toString(cl, 16);
				CN = Integer.toString(cl, 16);
				System.out.println("ORG! -> CL:"+CL);
			}
			else if (Instr.compareTo(EQU)==0){
				cl = AnaOperColoc (Oper);
				CL = Integer.toString(cl, 16);
				Dirr = " ";
				operi = "";
			}
			else if (Instr.compareTo(END)==0){
				Dirr = " ";
				operi = "";
				Dirr = " ";
				operi = "";
				Oper = "";
				CL = "";
				contadorb= "";
			}
		}
		
		if (cuentatokens==2){
			if(charval[0]==' '||charval[0]==';'){
				Etiqueta = "";
				Instr = tokens.nextToken();
				Oper =  tokens.nextToken();
				
				if(Instr.compareTo(ORG)!=0 && Instr.compareTo(EQU)!=0 && Instr.compareTo(END)!=0 )
				{	

					Typex = AnaOper (Oper);

					String IMM = "IMM";
					if(Typex.compareTo(IMM)==0){
						Dirr = Busqueda (Instr, IMM);
						Dirr = "IMM";
					}
					else{
						Dirr = Busqueda (Instr, Typex);
						Acu = Dirr;
						//Dirr = Acu;
					}

				}
				else if (Instr.compareTo(ORG)==0){
					cl = AnaOperColoc (Oper);
					CL = Integer.toString(cl, 16);
					CN = Integer.toString(cl, 16);
					Dirr = " ";
					operi = "";
				}
				else if (Instr.compareTo(EQU)==0){
					cl = AnaOperColoc (Oper);
					CL = Integer.toString(cl, 16);
					Dirr = " ";
					operi = "";
				}
				else if (Instr.compareTo(END)==0){
					Dirr = " ";
					operi = "";
					Oper = "";
					CL = "";
					contadorb= "";
				}
			}
			else {
				Etiqueta = tokens.nextToken();
				codver = Etiqueta.toCharArray();
				if(Etiqueta.length() > 8 || codver[0] < 'A' || codver[0] > 'z' ){
					Etiqueta = ""; //Etiqueta con error
				}
				Instr = tokens.nextToken();
				Oper =  "";

				Acu = "INH";
				if(Instr.compareTo(ORG)!=0 && Instr.compareTo(EQU)!=0 && Instr.compareTo(END)!=0 )
				{
					Acu = "INH";
					Dirr = Busqueda (Instr, Acu);
					Dirr = Acu;
					//System.out.println("XDir = "+Dirr);
				}
				else if (Instr.compareToIgnoreCase(ORG)==0){
					Dirr = " ";
					operi = "";
					cl = AnaOperColoc (Oper);
					CL = Integer.toString(cl, 16);
					CN = Integer.toString(cl, 16);
					System.out.println("ORG! -> CL:"+CL);
				}
				else if (Instr.compareToIgnoreCase(EQU)==0){	
					cl = AnaOperColoc (Oper);
					CL = Integer.toString(cl, 16);
					Dirr = " ";
					operi = "";
				}
				else if (Instr.compareToIgnoreCase(END)==0){
					Dirr = " ";
					operi = "";
					Oper = "";
					CL = "";
					contadorb= "";
				}
			}
		}
		if (cuentatokens==1){
			Etiqueta = "";
			Instr = tokens.nextToken();
			Oper = "";
			
			Acu = "INH";
			Dirr = Busqueda (Instr, Acu);
			Dirr = Acu;
			Acu = Dirr;
			if (Instr.compareToIgnoreCase(END)==0){
				Dirr = " ";
				operi = "";
				Oper = "";
				
				contadorb= "";
			}
		}
		if (cuentatokens==0){
			Etiqueta = "";
			Instr = "";	
			Oper = "";
			Dirr = Busqueda (Instr, "");
		}
		String puerca[] = { Etiqueta, Instr, Oper, Dirr,  operi, CL,  contadorb };
		return puerca;
	}
	//Fin Funcion SplitTokens
	
	//
	
	
	//inicio Impresion
	
	//Fin Impresion
	
	//InicioBusqueda
	public String Busqueda(String Instr, String Typex){
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
		String instxt = null;
		String diri = null;
		String direxx = "";
		operi = "";
		xopero = "";
		Object cbytes = null;
		int count = 0;
		try 
		{
			archivo = new File ("TABOP12.txt");
	        fr = new FileReader (archivo);
	        br = new BufferedReader(fr);
	        String linea;
	        while((linea=br.readLine())!=null){
	        	
	        	StringTokenizer instrsplit = new StringTokenizer(linea, "|");
	        	
	        		instxt = instrsplit.nextToken();
	        		diri = instrsplit.nextToken();
	        		operi = instrsplit.nextToken();
	        		cbytes  = instrsplit.nextToken();
	        		if (Instr.compareTo(instxt)==0 ){
	        			//System.out.println("Entro!");
	        			count++;
	        			if(Typex.compareTo(diri)==0 ){
	        				//Clocd += cb;
	        				direxx=diri;
	        				Clocd = Integer.parseInt((String) cbytes, 10);
	        				//System.out.println("SUMAXX : "+ Clocd);
	        				int Clocd2 = Integer.parseInt(LOCA,16);
	        				Clocd += Clocd2;
	        				CL = Integer.toString(Clocd, 16); 
	        				//System.out.println("DIREXX : "+ direxx);
	        				break;
	        				
	        			}
	        			
	        		}
	        		else {
	        			operi = "";
	        		}
	        }
	       // System.out.println("Contador = "+count);
	        if (count==1 && Typex.compareTo(diri)!=0 && xrel == 1){
	        	String REL = "REL";
	        	xrel++;
	        	Busqueda (Instr, REL);
	        	direxx = "REL";
	        }
		}catch (Exception ie)
		{
			ie.printStackTrace();
		}
		//System.out.println("DIREXX Antes de Regresar = "+direxx);
		contadorb= (String) cbytes;
		return direxx;
	}
	//Fin Busqueda
	
	//Inicio Analizador Operando Coloc -Específico para Direcciones de Memoria (HEXA-BIN-DEC-OCT)
	 public int AnaOperColoc(String Oper){
		char opi[];
		StringTokenizer opersin = new StringTokenizer(Oper,"#$@%");
		int opp = 0;
		opi = Oper.toCharArray();
		
		if (opi[0]=='$'){
			//System.out.println("HEXA");
			Acu = opersin.nextToken();
			//System.out.println(Acu);
			opp = Integer.parseInt(Acu,16);
		}
		else if (opi[0]=='@'){
			//System.out.println("OCTA");
			Acu = opersin.nextToken();
			opp = Integer.parseInt(Acu,8);
		}
		else if (opi[0]=='%'){
			//System.out.println("BIN");
			Acu = opersin.nextToken();
			//System.out.println(Acu);
			opp = Integer.parseInt(Acu,2);
		}
		else if (opi[0]>='0' || opi[0]<='9')
		{
			Acu = opersin.nextToken();
			
			opp = Integer.parseInt(Acu,10);
		}
		return opp;
	}
	//Fin 
	 
	//Inicio Analizador Operando - Quita el [0] Y revisa que tipo de Valor son (HEXA-BIN-DEC-OCT)
	 public String AnaOper(String Oper){
		char opi[];
		StringTokenizer opersin = new StringTokenizer(Oper,"#$@%");
		int opp = 0;
		opi = Oper.toCharArray();
		String EXT = "EXT";
		String INM = "IMM";
		if (opi[0]>='A' && opi[0]<='Z'){
			Acu = EXT;
		}
		else {
			if (opi[0]=='#'){
				//System.out.println("GATO");
				
				Acu = INM;
			}
			else if (opi[0]=='$'){
				//System.out.println("HEXA");
				Acu = opersin.nextToken();
				//System.out.println(Acu);
				opp = Integer.parseInt(Acu,16);
				Acu = AnaOperando(opp );
			}
			else if (opi[0]=='@'){
				//System.out.println("OCTA");
				Acu = opersin.nextToken();
				opp = Integer.parseInt(Acu,8);
				Acu = AnaOperando(opp );
			}
			else if (opi[0]=='%'){
				//System.out.println("BIN");
				Acu = opersin.nextToken();
				//System.out.println(Acu);
				opp = Integer.parseInt(Acu,2);
				Acu = AnaOperando(opp );
			}
			else if (opi[0]>='0' || opi[0]<='9')
			{
				Acu = opersin.nextToken();
				System.out.println("Acux: "+Acu);
				//opp = Integer.parseInt(Acu,10);
				Acu = AnaOperando(opp );
				//System.out.println("Acuy: "+Acu);
				if(Acu.compareTo("DIR")==0){
					Acu = EXT;
				}
			}
		}
		return Acu;
	}
	//Fin 
	
	//Inicio AnaOperando
	public String AnaOperando (int opp){
		String YYY=null;
			if(opp >=0 && opp <= 255){
				YYY= "DIR";			}
			else if(opp >255 && opp <= 65535){
				YYY= "EXT";
			}
		return YYY;
	}
}
	