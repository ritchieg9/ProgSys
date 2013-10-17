import java.util.StringTokenizer;

public class Cadena {
	
	public int Revisadir(String linea){
		int vf = 0;
		String varend = "";
		StringTokenizer end = new StringTokenizer (linea);
		while(end.hasMoreTokens()){
			varend = end.nextToken();
			if(varend.compareTo("END")==0){
				vf = 1;
				break;
			}
			if(varend.compareTo("ORG")==0){
				vf = 2;
				break;
			}
			if(varend.compareTo("EQU")==0){
				vf = 3;
				break;
			}
			else{
				vf = 0;
			}
		}
		return vf;
	}
	
	String DeleteComment (String linea){
		
		String lusada = "";
		char charval[];
		charval = linea.toCharArray();
		int x=0;
		for(x=1; x < linea.length(); x++ ){		
			if(charval[x]==';'){
				lusada = linea.substring(0,x);
				x=linea.length();
			}	
		}
		if (x==linea.length()){
			lusada = linea;
		}
		return lusada;
	}
	
	public String[] Segmentos(String linea){
		
		String Etiqueta = "";
		String Instruccion = "";
		String Operando = "";
		char charval[];
		char codver[];
		charval = linea.toCharArray();
		
		StringTokenizer tokens = new StringTokenizer(linea);
		int cuentatokens = tokens.countTokens();
		
		if (cuentatokens==3){ //ETQ + INS + OPE
			Etiqueta = tokens.nextToken();
			codver = Etiqueta.toCharArray();
			if(Etiqueta.length() > 8 || codver[0] < 'A' || codver[0] > 'z' ){
				Etiqueta = ""; //Etiqueta con error
			}
			Instruccion = tokens.nextToken();
			Operando =  tokens.nextToken();
		}
		
		if (cuentatokens==2){ 
			if(charval[0]==' '||charval[0]==';'){ // ___ + INS + OPE
				Etiqueta = "";
				Instruccion = tokens.nextToken();
				Operando =  tokens.nextToken();
			}
			else { //ETQ + INS + ___
				Etiqueta = tokens.nextToken();
				codver = Etiqueta.toCharArray();
				if(Etiqueta.length() > 8 || codver[0] < 'A' || codver[0] > 'z' ){
					Etiqueta = ""; //Etiqueta con error
				}
				Instruccion = tokens.nextToken();
				Operando =  "";
			}
		}
		if (cuentatokens==1){
			Etiqueta = "";
			Instruccion = tokens.nextToken();
			Operando = "";
		}
		if (cuentatokens==0){
			Etiqueta = "";
			Instruccion = "";	
			Operando = "";
		}
		String puerca[] = { Etiqueta, Instruccion, Operando };
		return puerca;
	}
	
	public String AnalizarOperando (String Operando){
		String Direccionamiento = "";
		String EXT = "EXT";
		String INM = "IMM";
		
		char opi[];
		StringTokenizer opersin = new StringTokenizer(Operando,"#$@%,");
		int opp = 0;
		opi = Operando.toCharArray();
		
		if (opi[0]>='A' && opi[0]<='Z'){
			Direccionamiento = EXT;
		}
		
		else {
			if (opi[0]=='#'){
				Direccionamiento = INM;
				System.out.println("IMM");
			}
			else if (opi[0]=='$'){

				Operando = opersin.nextToken();
				opp = Integer.parseInt(Operando,16);
				System.out.println("HEXA: "+opp);
				Direccionamiento = AnaOperando(opp );
			}
			else if (opi[0]=='@'){

				Operando = opersin.nextToken();
				opp = Integer.parseInt(Operando,8);
				System.out.println("OCTA: "+opp);
				Direccionamiento = AnaOperando(opp );
			}
			else if (opi[0]=='%'){

				Operando = opersin.nextToken();
				opp = Integer.parseInt(Operando,2);
				System.out.println("BIN: "+opp);
				Direccionamiento = AnaOperando(opp );
			}
			else if (opi[0]>='0' || opi[0]<='9'){
				
				Operando = opersin.nextToken();
				opp = Integer.parseInt(Operando,10);
				System.out.println("DEC: "+opp);
				Direccionamiento = AnaOperando(opp );
			}
		}
		return Direccionamiento;
	}
	
	public String AnaOperando (int opp){
		String Direccionamiento="";
			if(opp >=0 && opp <= 255){
				Direccionamiento= "DIR";			}
			else if(opp >255 && opp <= 65535){
				Direccionamiento= "EXT";
			}
		return Direccionamiento;
	}
}
