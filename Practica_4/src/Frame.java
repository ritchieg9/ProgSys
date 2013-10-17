import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JTextField;


public class Frame extends JFrame {
	Vector <Alojamiento> lista = new Vector <Alojamiento> ();
	JFileChooser fc = new JFileChooser();
	String Etiqueta= null;
	String Instr= null;
	String Oper= null;
	String Nul= null;
	String Dirr = null;
	String Cloc = null;
	String Acu=null;
	String LOCAN = "0000";
	String LOCA = "0000";
	int xrel = 1;
	int Clocd = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	FileNameExtensionFilter filter = new FileNameExtensionFilter (".txt and .asm files", "txt", "asm");
	private JTextField textField;
	String RutaOpen = null;
	private JTextArea textArea;
	private String cbytes;
	
	public Frame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenu mnMenu = new JMenu("Menu 2");
		menuBar.add(mnMenu);
		
		JMenuItem mntmItem = new JMenuItem("Item1");
		mnMenu.add(mntmItem);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("");
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		contentPane.add(textArea, BorderLayout.CENTER);
		
		//Inicio Boton Abrir
		final JButton btnAbrir = new JButton("");
		btnAbrir.setIcon(new ImageIcon("open.png"));
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		toolBar.add(btnAbrir);
		//Fin Boton Abrir
		
		//Inicio MenuItem Abrir
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {					
				abrir();
			}
		});
		mnArchivo.add(mntmAbrir);
		//Fin MenuItem Abrir
	
		//Inicio MenuItem Guardar Como
		JMenuItem mntmGuardar = new JMenuItem("Guardar Como");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuardarComo();
			}
		});
		mnArchivo.add(mntmGuardar);
		//Fin MenuItem Guardar Como
		
		//Inicio MenuItem Guardar
		JMenuItem mntmGuardar_1 = new JMenuItem("Guardar");
		mntmGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Guardar();
			}
		});
		mnArchivo.add(mntmGuardar_1);
		//Fin MenuItem Guardar
		
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
		
		
		//Inicio Boton Guardar
		JButton btnGuardar = new JButton("");
		btnGuardar.setIcon(new ImageIcon("save.gif"));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Guardar();
			}
		});
		toolBar.add(btnGuardar);
		//Fin Boton Guardar
		
		//Inicio Boton Clear
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		button.setIcon(new ImageIcon("clear.gif"));
		toolBar.add(button);
		//Fin Boton Clear
		
		//Inicio Boton Info
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String gtext = null;
				//imprimir();
				gtext = textArea.getText();
				//System.out.println(gtext);
				StringTokenizer cutxt = new StringTokenizer (gtext, "\n");
				Clocd = 0;
				while(cutxt.hasMoreTokens()){
					xrel = 1;
					analizador(cutxt.nextToken());
				}
				imprimir();
			}
		});
		button_1.setIcon(new ImageIcon("info.gif"));
		toolBar.add(button_1);
		//Fin Boton Info
		
		textField = new JTextField();
		toolBar.add(textField);
		textField.setColumns(10);
		
		//Inicio Boton Agregar Texto en Append
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String txtfld = textField.getText();
				textField.setText("");
				textField.grabFocus();
				textArea.append(txtfld + "\n");
			}
		});
		toolBar.add(btnAgregar);
		//Fin Boton Agregar Texto en Append
	}
	
	//Inicio Funcion Abrir
	void abrir() {
		int iResp;
		String strTexto = null;
		Vector<String> alLinea = null;
		Archivo leerArchivo = null;
		try 
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(filter);
			fileChooser.setDialogTitle("Abrir Archivo");
			iResp = fileChooser.showOpenDialog(fileChooser);
			if (iResp == JFileChooser.OPEN_DIALOG){
				
				leerArchivo = new Archivo ();
				alLinea = leerArchivo.LeeArchivo(fileChooser.getSelectedFile().toString());
				RutaOpen= fileChooser.getSelectedFile().toString();
				if(alLinea.size() > 0){
					textArea.setText("");
					for (int iIndice = 0; iIndice < alLinea.size(); iIndice++){
						strTexto = (String) alLinea.get(iIndice);
						textArea.append(strTexto+"\n");
					}
				}
			}
		}catch (Exception ie)
		{
			ie.printStackTrace();
		}	
	}
	//Fin Funcion Abrir
	
	//Inicio Funcion GuardarComo
	void GuardarComo(){
		
		fc.setDialogTitle("Guardar Archivo");
		int returnVal = fc.showSaveDialog(Frame.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String txta = textArea.getText();
			Archivo gcomo = new Archivo ();
			RutaOpen = gcomo.GuardaArchivo(txta, fc, RutaOpen);
			
		} else {
			JOptionPane.showMessageDialog (null, "Cancelado");
		}
	}
	//Fin Función GuardarComo
	
	//Inicio Función Guardar
	void Guardar(){
		if (RutaOpen != null) {
			Archivo guardar = new Archivo ();
			String txta = textArea.getText();
			RutaOpen =guardar.GuardaArchivo(txta, fc, RutaOpen);
			
		} else {
			GuardarComo();
		}
	}
	//Fin Función Guardar
	
	//Inicio Funcion Analizador
	void analizador(String strTexto){
		char charval[];
		charval = strTexto.toCharArray();
		String subusado = null;
		int x=0;
		for(x=1; x < strTexto.length(); x++ ){		
			if(charval[x]==';'){
				subusado = strTexto.substring(0,x);
				x=strTexto.length();
				Cortador(subusado);
			}	
		}
		if (x==strTexto.length()){
			Cortador(strTexto);
		}
	}
	// Fin Funcion Analizador
	
	//Inicio Funcion Split Tokens
	void Cortador(String cadena){
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
				//System.out.println("Es un Puto ORG ");
				Clocd = AnaOperColoc (Oper);
				LOCA = Integer.toString(Clocd, 16);
				LOCAN = Integer.toString(Clocd, 16);
				//System.out.println("C Localidades = "+LOCA);
				Dirr = " ";
			}
			else if (Instr.compareTo(EQU)==0){
				Clocd = AnaOperColoc (Oper);
				LOCAN = Integer.toString(Clocd, 16);
				Dirr = " ";
			}
			else if (Instr.compareTo(END)==0){
				Dirr = " ";
			}
		}
		if (cuentatokens==2){
			if(charval[0]==' '||charval[0]==';'){
				Etiqueta = "";
				Instr = tokens.nextToken();
				Oper =  tokens.nextToken();
				//System.out.println("\n\n2 Toks I+O Instrucción = "+ Instr);
				
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
					}
					//System.out.println("XDirr = "+ Dirr);
				}
				else if (Instr.compareTo(ORG)==0){
					Clocd = AnaOperColoc (Oper);
					LOCA = Integer.toString(Clocd, 16);
					LOCAN = Integer.toString(Clocd, 16);
					//System.out.println("C Localidades = "+LOCA);
					Dirr = " ";
				}
				else if (Instr.compareTo(EQU)==0){
					Clocd = AnaOperColoc (Oper);
					LOCAN = Integer.toString(Clocd, 16);
					Dirr = " ";
				}
				else if (Instr.compareTo(END)==0){
					Dirr = " ";
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
				//System.out.println("\n\n2 Toks E+I Instrucción = "+ Instr);
				Acu = "INH";
				if(Instr.compareTo(ORG)!=0 && Instr.compareTo(EQU)!=0 && Instr.compareTo(END)!=0 )
				{
					Acu = "INH";
					Dirr = Busqueda (Instr, Acu);
					Dirr = Acu;
					//System.out.println("XDir = "+Dirr);
				}
				else if (Instr.compareToIgnoreCase(ORG)==0){
					//System.out.println("Es un Puto ORG ");
					Clocd = AnaOperColoc (Oper);
					//System.out.println("Clocd "+Clocd);
					LOCA = Integer.toString(Clocd, 16);
					LOCAN = Integer.toString(Clocd, 16);
					Dirr = " ";
				}
				else if (Instr.compareToIgnoreCase(EQU)==0){	
					Clocd = AnaOperColoc (Oper);
					LOCAN = Integer.toString(Clocd, 16);
					Dirr = " ";
				}
				else if (Instr.compareToIgnoreCase(END)==0){
					Dirr = " ";
				}
			}
		}
		if (cuentatokens==1){
			Etiqueta = "";
			Instr = tokens.nextToken();
			Oper = "";
			Dirr = Busqueda (Instr, "");
			Acu = Dirr;
		}
		if (cuentatokens==0){
			Etiqueta = "";
			Instr = "";
			Oper = "";
			Dirr = Busqueda (Instr, "");
			Acu = Dirr;
		}
		
		if (Instr.compareTo(EQU)==0 || Instr.compareTo(ORG)==0){
			agregarZapatos(Etiqueta, Instr, Oper, Dirr, LOCAN);
		}
		else{
			agregarZapatos(Etiqueta, Instr, Oper, Dirr, LOCAN);
		}
		LOCAN=LOCA;
	}
	//Fin Funcion SplitTokens
	
	//
	void agregarZapatos (String Etiqueta, String Instr, String Oper, String Dirr, String CLocal) {
		lista.add(new Alojamiento (Etiqueta, Instr, Oper, Dirr, CLocal));
	}
	//
	
	//inicio Impresion
	void imprimir () {
		int nlinea =1;
		String lst= "";
		String tbs= "";
		for (int i =0; i < lista.size(); i++, nlinea++ ) {
			System.out.println("\n*Linea "+ nlinea);	
			lst += lista.get(i).imprimirVector(nlinea);
			tbs += lista.get(i).escribir();
		}
		System.out.println("\n*LST\n"+ lst);
		Archivo lstx = new Archivo ();
		lstx.GuardaArchivo(lst, fc, "Prueba.lst");
		System.out.println("\n*TBS\n"+ tbs);
		lstx.GuardaArchivo(tbs, fc, "Prueba.tbs");
	}
	//Fin Impresion
	
	//InicioBusqueda
	public String Busqueda(String Instr, String Typex){
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
		String instxt = null;
		String diri = null;
		String direxx = "";
		String operi = null;
		cbytes = null;
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
	        				Clocd = Integer.parseInt(cbytes, 10);
	        				//System.out.println("SUMAXX : "+ Clocd);
	        				int Clocd2 = Integer.parseInt(LOCA,16);
	        				Clocd += Clocd2;
	        				LOCA = Integer.toString(Clocd, 16); 
	        				//System.out.println("DIREXX : "+ direxx);
	        			}
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
		
		if (opi[0]=='#'){
			//System.out.println("GATO");
			String INM = "IMM";
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
			
			opp = Integer.parseInt(Acu,10);
			Acu = AnaOperando(opp );
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