
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Alojamiento {
	
	Vector <String> v = new Vector <String> ();
	
	private static String vix = "";
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
	private String Operx= "";
	private String aa = "";
	private String Subb = "";
	private static String Val = "";
	private static String Topo = "";
	private static String Gloca = "";
	private static String Bloca = "";
	private static String nanys = "";
	private static int nany = 0;
	
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
			if(opi[0]>='A' && opi[0]<='Z' && opi[1]>='A' && opi[1]<='Z'){
				String operx = BusquedaTBS(topoperando);
			//	System.out.println ("Valor ETQ : " + operx);
				Acu = wracu (gcConb, Acu, operx, gdirr);
				
			}
			else{
			//Analizar Operando
				if(gdirr.compareTo("EXT")==0 || gdirr.compareTo("IMM")==0 || gdirr.compareTo("DIR")==0 || gdirr.compareTo("REL")==0 ){
					String opera = AnalizaOP(topoperando);
					while((opera.length()%2)==1){
						opera="0"+opera;
					}
					Acu = wracu (gcConb, Acu, opera, gdirr);
				}
				else if( gdirr.compareTo("IDX")==0 || gdirr.compareTo("IDX1")==0 || gdirr.compareTo("IDX2")==0 || gdirr.compareTo("[IDX2]")==0 || gdirr.compareTo("[D,IDX]")==0){
					String opera = "";
					Acu = wracu (gcConb, Acu, opera, gdirr);
				}
				
			}
		
		}
		//System.out.println ("Acu : " + Acu);
		
		
		ardillacu=gcloca+"\t\t\t"+Acu+"\t\t\t"+Etiquetav+"\t\t\t"+Instruccionv+"\t\t\t"+topoperando;
		
		
		 Topo = Acu;
		s19(gcloca);
		
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
		int xx=0;
		char xy [];
		xy = Subb.toCharArray();
		
		//System.out.println("WRACU:: "+gdirr);
		//System.out.println("OPERX:: "+Subb);
		
		if(gdirr.compareTo("IDX")==0 || gdirr.compareTo("IDX1")==0 || gdirr.compareTo("IDX2")==0 || gdirr.compareTo("[IDX2]")==0 || gdirr.compareTo("[D,IDX]")==0){
			
			//System.out.println("IDY_Y_Y");
			//System.out.println("OPER "+Topoperandor);
			String v =analizaidx(Topoperandor, gdirr);
			String Subb1 = "";
			String Subb2 = "";
			System.out.println("V "+v);
			int xrr = Integer.parseInt(v, 2);
			v = Integer.toString(xrr, 16);
			System.out.println("V2 "+v);
			
			if(v.length()<2)
				v ="0"+v;
			
			
			
			if(cacu==3){
				a1= acucut.nextToken();
				a2= acucut.nextToken();
				
				a2= v;
				//System.out.println("3333");
				xx = Integer.parseInt(Subb);
				Subb=Integer.toHexString(xx);
				xx = Subb.length();
				if(xx >2){
					Subb = Subb.substring(xx-2, xx);
				}
				while(Subb.length()<2){
					Subb = "0"+Subb;
				}
				
				Acu = a1+" "+a2+" "+Subb;
			}
			else if(cacu==5){
				a1= acucut.nextToken();
				a2= acucut.nextToken();
				a3= acucut.nextToken();
				
				a3= v;
				System.out.println("A3 "+a3);
				xx = Integer.parseInt(Subb);
				Subb=Integer.toHexString(xx);
				while(Subb.length()<4){
					Subb = "0"+Subb;
				}
				Subb1 = Subb.substring(0,2);
				Subb2 = Subb.substring(2, 4);
				Acu = a1+" "+a2+" "+a3+" "+Subb1+" "+Subb2;
			}
			else if(cacu==4){
				a1= acucut.nextToken();
				a2= acucut.nextToken();
				a2= v;
				//System.out.println("4444");
				xx = Integer.parseInt(Subb);
				Subb=Integer.toHexString(xx);
				while(Subb.length()<4){
					Subb = "0"+Subb;
				}
				Subb1 = Subb.substring(0,2);
				Subb2 = Subb.substring(2, 4);
				Acu = a1+" "+a2+" "+Subb1+" "+Subb2;
			}
			else if(cacu==2){
				a1= acucut.nextToken();
				a2= acucut.nextToken();
				a2= v;
				Acu = a1+" "+a2;
			}
			else if(cacu==1){
				//System.out.println("1111");
				xx = Integer.parseInt(Subb);
				Subb=Integer.toHexString(xx);
				Acu = a1+" "+a2+Subb;
			}			
			else {
				//System.out.println("ELSE");
				xx = Subb.length();
				if(xx>2){
					Subb = Subb.substring(xx-2, xx);
				}
				Acu = a1+" "+a2+" "+Subb;
			}
			//System.out.println("FINAL: "+Acu);
		}
		
		else if(gdirr.compareTo("REL")==0){
			char opi[];
			String rr="";
			opi = Acu.toCharArray();
			//System.out.println("REEEEEEEEL");
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
					//System.out.println("OCTAA dec:: "+opp);
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
			else if (opi[0]>='0' && opi[0]<='9')
			{
				Val = opersin.nextToken();
				opp = Integer.parseInt(Val,10);
				opera =  Integer.toString(opp, 16);
			}
		Topoperandor = opera;	
		return opera;
		
	}
	
	public String tratarel(String Instruccionr, String gdir, String CONTL, String Topoperandor, int tipo){
		System.out.println("REL LD");
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
			//System.out.println("Tipo rr");
			//System.out.println("Topo: "+Topoperandor);
			yy = Integer.parseInt(Topoperandor, 16);
			rr = yy - (cc + 2);
			xx = Integer.toHexString(rr);
			//System.out.println("INT: "+rr);
			//System.out.println("RR: "+xx);
			
			
			x = xx.length();
			xx = xx.substring(x-2,x);
			
			break;
		case 2:
			//System.out.println("Tipo qq rr");
			//System.out.println("Topo: "+Topoperandor);
			yy = Integer.parseInt(Topoperandor, 16);
			rr = yy - (cc + 4);
			xx = Integer.toHexString(rr);
		//	System.out.println("INT: "+rr);
		//	System.out.println("RR: "+xx);
			
			x = xx.length();
			xx = xx.substring(x-4,x);
			zz = xx.substring(2,4);
			xx = xx.substring(0,2)+" "+zz;
			
			break;
		case 3:
		//	System.out.println("Tipo lb rr");
			
			ist = lb.nextToken();
			System.out.println("ist "+ist);
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
			//System.out.println("Instruccion: "+it);
			yy = Integer.parseInt(xx);
			//System.out.println("Valor Despues del ,: "+yy);
			cc+=4;
			if(yy > cc){
				//NEGATIVO
				nlb = it + rg;
				xx = Integer.toHexString(nlb);
			//	System.out.println("NLB: "+xx);
				yy = Integer.parseInt(zz, 10);
			//	System.out.println("YY: "+yy);
				rr = yy - cc;
				c3 = Integer.toHexString(rr);
			//	System.out.println("RR: "+c3);
				y=c3.length();
				zz = c3.substring(y-2,y);
				xx = xx+" "+zz;
				
			//	System.out.println("FIN: "+xx);
				
			}
			else{
				//POSITIVO
				nlb = it + rg+16;
				xx = Integer.toHexString(nlb);
			//	System.out.println("PLB: "+xx);
			//	System.out.println("CC: "+cc);
				yy = Integer.parseInt(zz, 10);
		//		System.out.println("YY: "+yy);
				rr = yy - cc;
				c3 = Integer.toHexString(rr);
			//	System.out.println("RR: "+c3);
				y=c3.length();
				zz = c3.substring(y-2,y);
				xx = xx+" "+zz;
				
			//	System.out.println("FIN: "+xx);
			}
			break;
		default:
			break;
		}
		return xx;
	}
	
	public String analizaidx(String Oper, String Acu){
		StringTokenizer tok = new StringTokenizer(Oper," ,][");
		char x[]=null;
		Operx=Oper;
		String nn = "";
		String rr = "";
		String xb = "";
		 aa = "";
		x = Oper.toCharArray();	
		int cidx = 0;
		int ctok = tok.countTokens();
		int nid=0;
		for (int i = 0 ; i< Oper.length() ; i++){
			if (Oper.charAt(i)=='['){
				cidx = 1;
				break;
			}
		}
	//	System.out.println("ANALISIS: "+Oper);
		//System.out.println("CTOK: "+ctok);
		
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
		//	System.out.println("Primera Parte (String): "+ aa);
		//	System.out.println("Segunda Parte (X,Y,SP,PC): "+rr);
			if((aa.compareTo("A")==0 || aa.compareTo("B")==0 || aa.compareTo("D")==0 )&& Acu.compareTo("[D,IDX]")==0 && cidx !=1){
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
						System.out.println("PREPOST: ");
						break;
					}
					else {
						//nid = Integer.parseInt(aa, 10);
						if(Acu.compareTo("IDX")==0){
							Acu = "IDX";
				//			System.out.println("IDX 5:" );
							xb = idx (aa, rr, xb, 2); //idx5
						}
						else if (Acu.compareTo("IDX1")==0){
							Acu = "IDX1";
					//		System.out.println("IDX 9:" );
							xb = idx (aa, rr, xb, 3); //idx9
						}
						else if (Acu.compareTo("IDX2")==0){
							Acu = "IDX2";
					//		System.out.println("IDX 16:" );
							xb = idx (aa, rr, xb, 4); //idx16
						}
						else if (Acu.compareTo("[IDX2]")==0){
							Acu = "[IDX2]";
					//		System.out.println("[IDX2] xx" );
							xb = idx (aa, rr, xb, 5); //[IDX2]
						}
						else if (Acu.compareTo("[D,IDX]")==0){
							Acu = "[D,IDX]";
						//	System.out.println("[D,IDX] xx" );
							xb = idx (aa, rr, xb, 6); //[D,IDX]
						}
					}
				}
			}
		}
		//System.out.println("AA/NN: "+aa);
		//System.out.println("RR: "+rr);
		// System.out.println("XB: "+xb);
		
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
			//System.out.println("IDX 5: "+xb);
		}
		if(tipo == 3){
			nn = revisabase (nn, Operx);
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
			//System.out.println("IDX 9 PUTOS: "+xb);
		}
		if(tipo == 4){
			rr = revisarr(rr);
			nn = revisabase (nn, Operx);
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
			nn = revisabase (nn, Operx);
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
		return xb;
		
	}
	
	public String revisarr(String rr){
		rr = rr.toUpperCase();
		if(rr.compareTo("X")==0 ){
			rr = "00";
		}
		if(rr.compareTo("Y")==0 ){
			rr = "01";
		}
		if(rr.compareTo("SP")==0 ){
			rr = "10";
		}
		if(rr.compareTo("PC")==0 ){
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
					subusado = revisabase(subusado, Operx);
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
					subusado = revisabase(subusado, Operx);
				}
					
			}
		}
		if(subusado.length() > 0 && subusado.compareTo("D")!=0 && subusado.compareTo("B")!=0 && subusado.compareTo("A")!=0){
			bin = Integer.parseInt(subusado, 10);
			//System.out.println("BIN : "+bin);
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
		else if(bin>=0 && bin<=65535 && id==25){
			Acu = "[IDX2]";
		}
		
	//	System.out.println("ANA ACU: "+Acu);
		
		return Acu;
		
	}
	
	public String revisabase(String subusado, String Oper){
		char opi[];
		int x = subusado.length();
		opi = subusado.toCharArray();
		
		if (opi[0]=='@'){
			subusado = subusado.substring(1,x);
			x = Integer.parseInt(subusado, 8);
			subusado = Integer.toString(x);
		}
		else if (opi[0]=='%' ){
			//System.out.println("BIN");
			subusado = subusado.substring(1,x);
			x = Integer.parseInt(subusado, 2);
			subusado = Integer.toString(x);
		}
		//System.out.println("SUBUSADO: "+subusado);
		Subb = subusado;
		return subusado;
		
	}
	
	public void s19(String gcloca){
		Cadenatxt viz = new Cadenatxt ();
		char vallo[];
		String val2 = "";
		int cacus=0;
		int ggl = 0;
		String cutgl1="";
		String cutgl2="";
		String Liam = "";
		
		
		StringTokenizer valli = new StringTokenizer (Topo, " ");
		cacus = valli.countTokens();
		
		if(Topo.length()==0 && Gloca.length()==0){
			//NADA D:;
			System.out.println("L1");
		}
		else if(Topo.length()!=0 && Gloca.length()==0){
			System.out.println("L2");
			Gloca = gcloca;
			Bloca = Gloca;
			for (int i=0; i<cacus ; i++){
				val2 = valli.nextToken();
				Val = Val +" "+ val2;
				nany++;
			}
			ggl = Integer.parseInt(Bloca, 16);
			ggl += nany;
			nanys = Integer.toHexString(ggl);
			
			
		}
		else if(Topo.length()!=0 && Gloca.length()!=0 && nany<16){
			System.out.println("L3");
			for (int i=0; i<cacus ; i++){
				if(nany <16){
					val2 = valli.nextToken();
					Val = Val +" "+ val2;
					nany++;
					ggl = Integer.parseInt(Bloca, 16);
					ggl += nany;
					nanys = Integer.toHexString(ggl);
				}
				else {
					System.out.println("Ardilla Endemoniada!");
					while(Gloca.length()<4){
						Gloca = "0"+Gloca;
					}
					cutgl1 = Gloca.substring(0, (Gloca.length()-2));
					cutgl2 =  Gloca.substring(2, Gloca.length());
					Gloca = cutgl1+" "+cutgl2+" ";
					v.add(Gloca+" "+Val+"\n");
					
					vix = Gloca+Val;
					System.out.println("Oasis: "+vix);
					
					StringTokenizer cviz = new StringTokenizer(vix," \n");
					int ctot = cviz.countTokens();
					ctot++;
					String ct = Integer.toHexString(ctot);
					while(ct.length()<2){
						ct = "0"+ct;
					}
					vix = "S1   "+ct+"   "+vix;
					
					Liam = viz.S191(vix);
					System.out.println("LIAM: "+Liam);
					vix = vix + Liam + "\n";
					Gloca = nanys;
					Bloca = nanys;
					nany = 0;
					Val = "";
					val2 = valli.nextToken();
					Val = Val +" "+ val2;
					nany++;
				}
				
			}
		}
		else if(Topo.length()==0 && Gloca.length()!=0){
			System.out.println("\nL4\n");
			while(Gloca.length()<4){
				Gloca = "0"+Gloca;
			}
			v.add(Gloca+" "+Val+"\n");
			cutgl1 = Gloca.substring(0, (Gloca.length()-2));
			cutgl2 =  Gloca.substring(2, Gloca.length());
			Gloca = cutgl1+" "+cutgl2+" ";
			vix = Gloca+" "+Val;
			
			StringTokenizer cviz = new StringTokenizer(vix," \n");
			int ctot = cviz.countTokens();
			ctot++;
			String ct = Integer.toHexString(ctot);
			while(ct.length()<2){
				ct = "0"+ct;
			}
			vix = "S1   "+ct+"   "+vix;
			
			Liam = viz.S191(vix);
			vix = vix + Liam + "\n";
			System.out.println("LIAM: "+Liam);
			
			nany = 0;
			Val = "";
			Gloca = "";
			
			System.out.println("Sally.");
			for(int index=0; index < v.size(); index++){
			      System.out.println(v.get(index));
			}
			
		}
		
		System.out.println("Gloca: "+ Gloca);
		System.out.println("Valores: "+Val);
		System.out.println("Bloca: "+nany);
		System.out.println("GGL: "+nanys);
	}
	
}

