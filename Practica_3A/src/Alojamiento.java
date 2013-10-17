
import java.util.StringTokenizer;

public class Alojamiento {
	private String Etiqueta;
	private String Instruccion;
	private String Operando;
	private String Dirr;
	
	public Alojamiento (String Etiqueta, String Instruccion,String Operando, String Dirr){
		super();
		this.Etiqueta = Etiqueta;
		this.Instruccion = Instruccion;
		this.Operando = Operando;
		this.Dirr = Dirr;
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

	void imprimirVector (int nlinea) {
		String Etiquetav = this.getEtiqueta();
		String Instruccionv = this.getInstruccion();
		String topoperando= this.getOperando();
		String gdirr= this.getDirr();
		StringTokenizer opersplit = new StringTokenizer(topoperando, ",");
		int countoperando = 0;
		
		
		System.out.println ("Etiqueta: " + Etiquetav);
		System.out.println ("Instruccion: " + Instruccionv);
		System.out.println ("Operando(s): " );
		while(opersplit.hasMoreTokens()){
		System.out.println(opersplit.nextToken());
		countoperando++;
		}
		System.out.println ("Numero de Operandos: " + countoperando);
		System.out.println ("Direccionamientos: " + gdirr);
	}
	
}

