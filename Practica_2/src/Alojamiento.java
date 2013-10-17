import java.util.StringTokenizer;

public class Alojamiento {
	private String Etiqueta;
	private String Instruccion;
	private String Operando;
	
	public Alojamiento (String Etiqueta, String Instruccion,String Operando){
		super();
		this.Etiqueta = Etiqueta;
		this.Instruccion = Instruccion;
		this.Operando = Operando;
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

	
	void imprimirVector (int nlinea) {
		
		String topoperando= this.getOperando();
		StringTokenizer opersplit = new StringTokenizer(topoperando, ",");
		int countoperando = 0;
		
		System.out.println ("Etiqueta: " + this.getEtiqueta());
		System.out.println ("Instruccion: " + this.getInstruccion());
		System.out.println ("Operando(s): " );
		while(opersplit.hasMoreTokens()){
		System.out.println(opersplit.nextToken());
		countoperando++;
		}
		System.out.println ("Numero de Operandos: " + countoperando);
	}
}

