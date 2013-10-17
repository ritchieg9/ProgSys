import java.util.Vector;


public class Tabsim {
	//Vector <Alojamiento> lista = new Vector <Alojamiento> ();
	
	private String Etiqueta;
	private String Instruccion;
	private String Operando;
	private String Local;
	private String Dirr;

	public Tabsim (){
		
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
	
	void escribetab ( int sz){
		System.out.println("ARDILLA");
		System.out.println("SIZE= "+sz);
		for (int i =0; i < sz; i++ ) {
			System.out.println("\n*Linea "+ i);	
			String Etiquetav = this.getEtiqueta();
			String Instruccionv = this.getInstruccion();
			String topoperando= this.getOperando();
			String gdirr= this.getDirr();
			String gcloca = this.getLocal();
			System.out.println(gcloca);
			
		}
		
	}

}
