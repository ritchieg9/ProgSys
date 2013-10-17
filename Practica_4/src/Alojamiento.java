
import java.util.StringTokenizer;

public class Alojamiento {

	private String Etiqueta;
	private String Instruccion;
	private String Operando;
	private String Dirr;
	private String Local;
	
	public Alojamiento (String Etiqueta, String Instruccion,String Operando, String Dirr, String Local){
		super();
		this.Etiqueta = Etiqueta;
		this.Instruccion = Instruccion;
		this.Operando = Operando;
		this.Dirr = Dirr;
		this.Local = Local;
	}
	
	public String getEtiqueta() {
		return Etiqueta;
	}
	public void setEtiqueta(String Etiqueta) {
		this.Etiqueta = Etiqueta;
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

	String imprimirVector (int nlinea) {
		String Etiquetav = this.getEtiqueta();
		String Instruccionv = this.getInstruccion();
		String topoperando= this.getOperando();
		String gdirr= this.getDirr();
		String gcloca = this.getLocal();
		StringTokenizer opersplit = new StringTokenizer(topoperando, ",");
		String ardillacu= null;
		int countoperando = 0;
		
		
		System.out.println ("Etiqueta: " + Etiquetav);
		System.out.println ("Instruccion: " + Instruccionv);
		System.out.println ("Operando(s): " );
		while(opersplit.hasMoreTokens()){
		System.out.println(opersplit.nextToken());
		countoperando++;
		}
		System.out.println ("Numero de Operandos: " + countoperando);
		System.out.println ("Direccionamiento: " + gdirr);
		System.out.println ("Contador Localidad : " + gcloca);
		
		ardillacu=gcloca+"\t\t\t"+Etiquetav+"\t\t\t"+Instruccionv+"\t\t\t"+topoperando+"\n";
		if(Instruccionv.compareTo("")==0){
			return "";
		}
		else{
			return ardillacu;
		}
		
		 
		 
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
	
}

