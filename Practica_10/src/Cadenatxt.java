//Cadenatxt
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;


public class Cadenatxt {
	Vector <Alojamiento> lista = new Vector <Alojamiento> ();
	
	static String vix = "";
	int sum = 0;
	String Etiqueta= null;
	String xb = "";
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
		
		////System.out.println(cutxt);
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
			////System.out.println("\n\n3 Tokens Instruccion: "+ Instr);
			if (Instr.compareTo(ORG)!=0 && Instr.compareTo(EQU)!=0 && Instr.compareTo(END)!=0){ 
				Typex = AnaOper (Oper);
				////System.out.println("DIRR: "+Typex);
				
				//System.out.println("Regreso: "+Typex);
				if(Typex.compareTo("IDX")==0 || Typex.compareTo("IDX1")==0 || Typex.compareTo("IDX2")==0 ){
					xb = analizaidx(Oper);
				}
				
				String B = Busqueda (Instr, Typex);
				if (B.compareTo(Typex)==0){
					////System.out.println("El Dir Acu T Obtenido = "+Typex);
					Dirr = Typex;
				}
				else {
					////System.out.println("El Dir Acu B Obtenido = "+B);
					Dirr = B;
				}
			}
			else if (Instr.compareTo(ORG)==0){
				Dirr = " ";
				operi = "";
				cl = AnaOperColoc (Oper);
				CL = Integer.toString(cl, 16);
				CN = Integer.toString(cl, 16);
				//System.out.println("ORG! -> CL:"+CL);
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
					//System.out.println("Regreso: "+Typex);
					////System.out.println("DIRR: "+Typex);
					if(Typex.compareTo("IDX")==0 || Typex.compareTo("IDX1")==0 || Typex.compareTo("IDX2")==0 || Typex.compareTo("[IDX2]")==0 || Typex.compareTo("[D,IDX]")==0){
						xb = analizaidx(Oper);
					}
					else if(Instr.compareTo("DEC")==0){
						Dirr = Busqueda(Instr, "REL");
						Dirr = "REL";	
						operi = "73 hh ll";
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
					////System.out.println("XDir = "+Dirr);
				}
				else if (Instr.compareToIgnoreCase(ORG)==0){
					Dirr = " ";
					operi = "";
					cl = AnaOperColoc (Oper);
					CL = Integer.toString(cl, 16);
					CN = Integer.toString(cl, 16);
					//System.out.println("ORG! -> CL:"+CL);
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
	        			count++;
	        			if(Typex.compareTo(diri)==0 ){
	        				//Clocd += cb;
	        				direxx=diri;
	        				Clocd = Integer.parseInt((String) cbytes, 10);
	        				////System.out.println("SUMAXX : "+ Clocd);
	        				int Clocd2 = Integer.parseInt(LOCA,16);
	        				Clocd += Clocd2;
	        				CL = Integer.toString(Clocd, 16); 
	        				////System.out.println("DIREXX : "+ direxx);
	        				break;
	        				
	        			}
	        			
	        		}
	        		else if (Instr.compareTo("DEC")==0){
	        			operi = "73 00 45";;
	        		}
	        		else {
	        			operi = "";
	        		}
	        }
	       // //System.out.println("Contador = "+count);
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
		////System.out.println("DIREXX Antes de Regresar = "+direxx);
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
			////System.out.println("HEXA");
			Acu = opersin.nextToken();
			////System.out.println(Acu);
			opp = Integer.parseInt(Acu,16);
		}
		else if (opi[0]=='@'){
			////System.out.println("OCTA");
			Acu = opersin.nextToken();
			opp = Integer.parseInt(Acu,8);
		}
		else if (opi[0]=='%'){
			////System.out.println("BIN");
			Acu = opersin.nextToken();
			////System.out.println(Acu);
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
		StringTokenizer opersin = new StringTokenizer(Oper,"#$@%]");
		int opp = 0;
		String ixd="00";
		opi = Oper.toCharArray();
		String EXT = "EXT";
		String INM = "IMM";
		int i=0;
		
		if(opi[0]=='['){
			i=25;
			Acu = anaacuidx (Oper);
			//System.out.println("Regreso2: "+Acu);
		}
		else {
			for ( i=0; i<Oper.length() ; i++){
				if(opi[i]==','){
					i=25;
					////System.out.println("Entro IDX :D");
					ixd = "11";
					Acu = anaacuidx (Oper);
					//System.out.println("Regreso2: "+Acu);
					break;
				}
			}
		}
		
		
		if (opi[0]>='A' && opi[0]<='Z' && opi[1]>='A' && opi[1]<='Z'){
			////System.out.println("Entro EXT D:");
			Acu = EXT;
		}
		else  {
			if (opi[0]=='#' && i!=25){
				////System.out.println("GATO");
				
				Acu = INM;
			}
			else if (opi[0]=='$' && i!=25){
				////System.out.println("HEXA");
				Acu = opersin.nextToken();
				opp = Integer.parseInt(Acu,16);
			}
			else if (opi[0]=='@' && i!=25){
				////System.out.println("OCTA");
				Acu = opersin.nextToken();
				opp = Integer.parseInt(Acu,8);
				Acu = AnaOperando(opp, ixd );
			}
			else if (opi[0]=='%' && i!=25){
				////System.out.println("BIN");
				Acu = opersin.nextToken();
				////System.out.println(Acu);
				opp = Integer.parseInt(Acu,2);
				Acu = AnaOperando(opp ,  ixd);
			}
			else if (opi[0]>='0' && opi[0]<='9' && i!=25)
			{
				Acu = opersin.nextToken();
			//	//System.out.println("Acux: "+Acu);
				////System.out.println("WTF: "+opi[0]);
				//opp = Integer.parseInt(Acu,10);
				Acu = AnaOperando(opp, ixd );
			//	//System.out.println("XXX "+Acu);
				////System.out.println("Acuy: "+Acu);
				
			}
			else if(Acu.compareTo("DIR")==0){
				//	//System.out.println("EXT D: 2");
					Acu = EXT;
				}
		}
		return Acu;
	}
	//Fin 
	
	//Inicio AnaOperando
	public String AnaOperando (int opp, String idx){
		String YYY=null;
		////System.out.println("IDX: "+idx);
			if(opp >=0 && opp <= 255 ){
			//	//System.out.println("OPP: "+opp);
				////System.out.println("EXT D: 22");
				YYY= "DIR";			
			}
			if(opp >255 && opp <= 65535){
				YYY= "EXT";
				////System.out.println("EXT D: 3");
			}
			if( idx.compareTo("11")==0){
				////System.out.println("PUERCA");
				YYY = "IDX";
			}
			////System.out.println("YYY: "+YYY);
		return YYY;
	}
	
	public String analizaidx(String Oper){
		StringTokenizer tok = new StringTokenizer(Oper," ,][");
		char x[]=null;
		String nn = "";
		String rr = "";
		
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
		//	System.out.println("RR: "+rr);
			rr = revisarr (rr);
			x = rr.toCharArray();
		//	System.out.println("Primera Parte (String): "+aa);
		//	System.out.println("Segunda Parte (X,Y,SP,PC): "+rr);
			
			
			if((aa.compareTo("A")==0 || aa.compareTo("B")==0 || aa.compareTo("D")==0 ) && Acu.compareTo("[D,IDX]")==0 ){
				//IDX ACUMULADOR
				Acu = "IDXA";
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
			else if (Acu.compareTo("IDXA")==0){
				Acu = "IDX1";
				//System.out.println("IDX 9:" );
				xb = idx (aa, rr, xb, 7); //idx9
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
		//System.out.println("XXB: "+xb);
		
		return xb;
		
		
		
	}
	 
	public String idx(String nn, String rr, String xb, int tipo ){
		if(tipo == 1){
			String sig = "0";
			char s[] = rr.toCharArray();
			StringTokenizer rrx = new StringTokenizer(rr,"-+");
			rr = rrx.nextToken();
			rr = revisarr(rr);
			int bin = Integer.parseInt(nn,10);
			while(nn.length()<2){
				nn="0"+nn;
			}
			if(s[0]=='-' || s[0]=='+'){
				sig = "0";
				rr="-"+nn;
				System.out.println("rrrrrr: "+rr);
				//int xx = Integer.parseInt(rr, 2);
				//System.out.println("rrrrrr: "+xx);
			}
			else{
				//System.out.println("LINEA2  rr: "+rr+"NN: "+nn);
				sig = "1";
				int xx = Integer.parseInt(rr, 2) ;
				//System.out.println("rrrrrr: "+xx);
			}
			xb = rr+"1"+sig+nn;
			//System.out.println("XBPP: "+xb);
		}
		if(tipo == 2){
			rr = revisarr(rr);
			int bin = Integer.parseInt(nn,10);
			nn = Integer.toBinaryString(bin);
			////System.out.println("NNNN: "+nn);
			while(nn.length()<5){
				nn="0"+nn;
			}
			int cx = nn.length();
			if(cx > 4)
			nn = nn.substring(cx-5,cx);
			xb = rr+"0"+nn;
			//System.out.println("IDX 5: "+xb);
		}
		if(tipo == 3){
			nn = revisabase (nn);
			rr = revisarr(rr);
			int bin = Integer.parseInt(nn,10);
			String s="0";
			if(bin<0){
				s="1";
			}
			else{
				s="0";	
			}
			xb= "111"+rr+"00"+s;
		//	//System.out.println("IDX 9 PUTOS: "+xb);
		}
		if(tipo == 4){
			rr = revisarr(rr);
			nn = revisabase (nn);
			int bin = Integer.parseInt(nn,10);
			String s="0";
			if(bin<0){
				s="1";
			}
			else{
				s="0";	
			}
			xb= "111"+rr+"01"+s;
			//System.out.println("IDX 16 PUTOS: "+xb);
		}
		if(tipo == 5){
			rr = revisarr(rr);
			nn = revisabase (nn);
			int bin = Integer.parseInt(nn,10);
			xb= "111"+rr+"011";
			//System.out.println("[IDX2] PUTOS" +xb);
		}
		if(tipo == 6){
			rr = revisarr(rr);
		//	nn = revisabase (nn);
		//	int bin = Integer.parseInt(nn,10);
			xb= "111"+rr+"111";
			//System.out.println("[D,IDX] PUTOS" +xb);
		}
		if(tipo == 7){
			rr = revisarr(rr);
		//	nn = revisabase (nn);
		//	int bin = Integer.parseInt(nn,10);
			xb = "111"+rr+"1"+nn;
			//System.out.println("[D,IDX] PUTOS" +xb);
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
	
	public String anaacuidx(String Oper){
		//System.out.println("WIKI");
		char charval[];
		charval = Oper.toCharArray();
		String subusado = "";
		int x=0;
		int bin=-5000;
		int id = 0;
		if (charval[0]=='['){
			for(x=1; x < Oper.length(); x++ ){	
				
				if(charval[x]==',' ){
					subusado = Oper.substring(1,x);
					x=Oper.length();
					subusado = revisabase(subusado);
					id = 25;
					//System.out.println("SUBUSADO [: "+subusado);
				}
					
			}
		}
		else {
			for(x=1; x < Oper.length(); x++ ){	
				
				if(charval[x]==',' ){
					subusado = Oper.substring(0,x);
					x=Oper.length();
					subusado = revisabase(subusado);
				}
					
			}
		}
		if(subusado.length() > 0 && subusado.compareTo("D")!=0 && subusado.compareTo("B")!=0 && subusado.compareTo("A")!=0 && subusado.compareTo("SP")!=0 && subusado.compareTo("Y")!=0  ){
			bin = Integer.parseInt(subusado, 10);
			Acu = "IDX";
		}
		else{
			Acu="[D,IDX]";
		}
		
		if(bin >= -16 && bin <= 15){
			Acu ="IDX";
		}
		else if(bin>=-256 && bin<=255){
			Acu = "IDX1";
		}
		else if(bin>=0 && bin<=65535){
			Acu = "IDX2";
		}
		if(bin>=0 && bin<=65535 && id==25){
			Acu = "[IDX2]";
		}
		
		//System.out.println("ANA ACU: "+Acu);
		
		return Acu;
		
	}
	
	public String revisabase(String subusado){
		char opi[];
		int x = subusado.length();
		opi = subusado.toCharArray();
		if (opi[0]=='@'){
			////System.out.println("OCTA");
			subusado = subusado.substring(1,x);
			//System.out.println("OCTA: "+subusado);
			x = Integer.parseInt(subusado, 8);
			subusado = Integer.toString(x);
		}
		else if (opi[0]=='%' ){
			////System.out.println("BIN");
			//System.out.println("&&& : "+subusado);
			subusado = subusado.substring(1,subusado.length());
			//System.out.println("%%% : "+subusado);
			x = Integer.parseInt(subusado, 2);
			subusado = Integer.toString(x);
		}
		//System.out.println("SUBUSADO: "+subusado);
		return subusado;
		
	}
	
	public String S191 (String viz){
		
		int conv = 0;
		int ffff=65535;
		int vf = 0;
		String vff="";
		String tokn = "";
		String ctok = "";
		StringTokenizer cviz = new StringTokenizer(viz," \n S1");
		int ctot = cviz.countTokens();
		
		for (int x=0; x< ctot ; x++){
			tokn = cviz.nextToken();
			System.out.println("Tkn n."+x+" :"+tokn);
			conv = Integer.parseInt(tokn, 16);
			sum = sum + conv; 
		}
		
		System.out.println("SUM DEC: "+sum);
		vf = ffff - sum;
		System.out.println("VF DEC: "+vf);
		vff = Integer.toHexString(vf);
		vff = vff.substring(2, vff.length());
		System.out.println("VF HEX: "+vff);
		vix = vix + viz;
		System.out.println("VIX : "+vix);
		String blur =vix+"    "+vff+"\n";
		vix = blur;
		
		return vix;
	}
	
	
	public void scero(String Ruta){
		
		int gato = Ruta.lastIndexOf('\\');
		int sum = 0;
		int i = 0;
		String ii = "";
		String rux = Ruta.substring(gato+1, Ruta.length());
		String hx = "";
		String adh= "";
		System.out.println("Ruta: "+Ruta);
		System.out.println("Rux: "+rux);

		if(Ruta.length()!=0){
			for (  i = 0; i < rux.length(); ++i ) {
			       char c = rux.charAt( i );
			       int j = (int) c;
			       hx = Integer.toHexString(j);
			       System.out.println(hx);
			       sum += j;
			       adh = adh+" "+hx;
			}
			i = i+3;
			sum = sum + i + 3;
		}
		else{
			scero ("\\Prueba.asm");
		}
		ii = Integer.toHexString(i);
		adh = "S0"+"  "+ii+"  00 00 "+adh;
		System.out.println(" \n"+adh);
		
	}
	
	public void muestra (){

			System.out.println(""+vix);
			System.out.println("S3   03   00 00 \tfc");
		
	}
}
	