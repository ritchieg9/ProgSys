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
				//System.out.println("DIRR: "+Typex);
				if(Typex.compareTo("IDX")==0){
					analizaidx(Oper);
				}
				
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
					
					//System.out.println("DIRR: "+Typex);
					if(Typex.compareTo("IDX")==0){
						analizaidx(Oper);
					}
					
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
		String ixd="00";
		opi = Oper.toCharArray();
		String EXT = "EXT";
		String INM = "IMM";
		
		for (int i=0; i<Oper.length() ; i++){
			if(opi[i]==','){
				//System.out.println("Entro IDX :D");
				Acu = "IDX";
				ixd = "11";
			}
		}
		if (opi[0]>='A' && opi[0]<='Z' && opi[1]>='A' && opi[1]<='Z'){
			//System.out.println("Entro EXT D:");
			Acu = EXT;
		}
		else  {
			if (opi[0]=='#'){
				//System.out.println("GATO");
				
				Acu = INM;
			}
			else if (opi[0]=='$'){
				//System.out.println("HEXA");
				Acu = opersin.nextToken();
				//System.out.println(Acu);
				opp = Integer.parseInt(Acu,16);
				Acu = AnaOperando(opp, ixd );
			}
			else if (opi[0]=='@'){
				//System.out.println("OCTA");
				Acu = opersin.nextToken();
				opp = Integer.parseInt(Acu,8);
				Acu = AnaOperando(opp, ixd );
			}
			else if (opi[0]=='%'){
				//System.out.println("BIN");
				Acu = opersin.nextToken();
				//System.out.println(Acu);
				opp = Integer.parseInt(Acu,2);
				Acu = AnaOperando(opp ,  ixd);
			}
			else if (opi[0]>='0' && opi[0]<='9')
			{
				Acu = opersin.nextToken();
			//	System.out.println("Acux: "+Acu);
				//System.out.println("WTF: "+opi[0]);
				//opp = Integer.parseInt(Acu,10);
				Acu = AnaOperando(opp, ixd );
			//	System.out.println("XXX "+Acu);
				//System.out.println("Acuy: "+Acu);
				if(Acu.compareTo("DIR")==0){
				//	System.out.println("EXT D: 2");
					Acu = EXT;
				}
			}
		}
		return Acu;
	}
	//Fin 
	
	//Inicio AnaOperando
	public String AnaOperando (int opp, String idx){
		String YYY=null;
		//System.out.println("IDX: "+idx);
			if(opp >=0 && opp <= 255 ){
			//	System.out.println("OPP: "+opp);
				//System.out.println("EXT D: 22");
				YYY= "DIR";			
			}
			if(opp >255 && opp <= 65535){
				YYY= "EXT";
				//System.out.println("EXT D: 3");
			}
			if( idx.compareTo("11")==0){
				//System.out.println("PUERCA");
				YYY = "IDX";
			}
			//System.out.println("YYY: "+YYY);
		return YYY;
	}
	
	public String analizaidx(String Oper){
		StringTokenizer tok = new StringTokenizer(Oper," ,][");
		char x[]=null;
		String nn = "";
		String rr = "";
		String xb = "";
		String aa = "";
		x = Oper.toCharArray();				
		int ctok = tok.countTokens();
		int nid=0;
		//System.out.println("ANALISIS: "+Oper);
		////System.out.println("CTOK: "+ctok);
		
		if(ctok==1){
			//IDX 5p1
			nn = "00000";
			rr = tok.nextToken();
			rr = revisarr(rr);
			xb = rr+"0"+nn;
			
		}
		else{
			aa = tok.nextToken();
			rr = tok.nextToken();
			rr = revisarr (rr);
			x = rr.toCharArray();
			//System.out.println("Primera Parte (String): "+aa);
			//System.out.println("Segunda Parte (X,Y,SP,PC): "+rr);
			if(aa.compareTo("A")==0 || aa.compareTo("B")==0 || aa.compareTo("D")==0 || Acu.compareTo("[D,IDX]")!=0 ){
				//IDX ACUMULADOR
				if(aa.compareTo("A")==0){
					aa = "00";
				}
				if(aa.compareTo("B")==0){
					aa = "01";
				}
				if(aa.compareTo("D")==0){
					aa = "10";
				}
				xb = "111"+rr+"1"+aa;
			}
			else{
				for(int i=0; i<rr.length() ; i++){
					if(x[i]=='+' || x[i]=='-'){
						xb = idx(aa, rr, xb, 1); //prepost
						//System.out.println("PREPOST: ");
						break;
					}
					else {
						//nid = Integer.parseInt(aa, 10);
						if(Acu.compareTo("IDX")==0){
							Acu = "IDX";
							//System.out.println("IDX 5:" );
							xb = idx (aa, rr, xb, 2); //idx5
						}
						else if (Acu.compareTo("IDX1")==0){
							Acu = "IDX1";
							//System.out.println("IDX 9:" );
							xb = idx (aa, rr, xb, 3); //idx9
						}
						else if (Acu.compareTo("IDX2")==0){
							Acu = "IDX2";
							//System.out.println("IDX 16:" );
							xb = idx (aa, rr, xb, 4); //idx16
						}
						else if (Acu.compareTo("[IDX2]")==0){
							Acu = "[IDX2]";
							//System.out.println("[IDX2] xx" );
							xb = idx (aa, rr, xb, 5); //[IDX2]
						}
						else if (Acu.compareTo("[D,IDX]")==0){
							Acu = "[D,IDX]";
							//System.out.println("[D,IDX] xx" );
							xb = idx (aa, rr, xb, 6); //[D,IDX]
						}
					}
				}
			}
		}
		////System.out.println("AA/NN: "+aa);
		////System.out.println("RR: "+rr);
		//System.out.println("XB: "+xb);
		
		return xb;
		
		
		
	}
	 
	public String idx(String nn, String rr, String xb, int tipo ){
		if(tipo == 1){
			int nnbin = 0;
			String sig = "1";
			String prr = revisarridx(rr);
			
			if(rr.charAt(0)=='-' || rr.charAt(0)=='+' ){ //PRE-INCREMENTO
				nn ="-"+nn;
				sig = "0";
				nnbin = Integer.parseInt(nn, 10);
				if(nn.length()!=0){
					nn = Integer.toBinaryString(nnbin);
				}
				nn = nn.substring((nn.length()-4), nn.length());
			
				xb = prr+"1"+sig+nn;
				System.out.println("XB: "+xb);
			}
			else { //POST-INCREMENTO
				sig = "1";
				nnbin = Integer.parseInt(nn, 10);
				if(nn.length()!=0){
					nn = Integer.toBinaryString(nnbin);
				}
				nnbin = Integer.parseInt(nn, 2);
				nnbin = nnbin - 1;
				if(nn.length()!=0){
					nn = Integer.toBinaryString(nnbin);
				}
				while(nn.length()<4){
					nn="0"+nn;
				}	
				xb = prr+"1"+sig+nn;
				System.out.println("XB: "+xb);
			}
		}
		if(tipo == 2){
			rr = revisarr(rr);
			int bin = Integer.parseInt(nn,10);
			nn = Integer.toBinaryString(bin);
			//System.out.println("NNNN: "+nn);
			while(nn.length()<5){
				nn="0"+nn;
			}
			int cx = nn.length();
			if(cx > 4)
			nn = nn.substring(cx-5,cx);
			xb = rr+"0"+nn;
			System.out.println("XB5: "+xb);
		}
		
		return xb;
		
	}
	
	public String revisarr(String rr){
		rr = rr.toUpperCase();
		if(rr.compareTo("X")==0){
			rr = "00";
		}
		if(rr.compareTo("Y")==0){
			rr = "01";
		}
		if(rr.compareTo("SP")==0){
			rr = "10";
		}
		if(rr.compareTo("PC")==0){
			rr = "11";
		}
		return rr;
	}
	
	public String revisarridx(String rr){
		rr = rr.toUpperCase();
		if(rr.compareTo("-X")==0 || rr.compareTo("X+")==0){
			rr = "00";
		}
		if(rr.compareTo("-Y")==0 || rr.compareTo("Y+")==0){
			rr = "01";
		}
		if( rr.compareTo("-SP")==0 || rr.compareTo("SP+")==0){
			rr = "10";
		}
		if( rr.compareTo("-PC")==0 || rr.compareTo("PC+")==0){
			rr = "11";
		}
		return rr;
	}
}
	