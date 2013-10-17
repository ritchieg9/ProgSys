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
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JTextField;


public class Frame extends JFrame {
	Vector <Alojamiento> lista = new Vector <Alojamiento> ();
	String Cod= null;
	String Instr= null;
	String Oper= null;
	String Nul= null;
	String Dirr = null;
	String Cloc = null;
	int Clocd = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	FileNameExtensionFilter filter = new FileNameExtensionFilter (".txt and .asm files", "txt", "asm");
	private JTextField textField;
	String comida = null;
	private JTextArea textArea;
	//private JOptionPane verificar;
	
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
			iResp = fileChooser.showSaveDialog(fileChooser);
			if (iResp == JFileChooser.OPEN_DIALOG){
				
				leerArchivo = new Archivo ();
				alLinea = leerArchivo.LeeArchivo(fileChooser.getSelectedFile().toString());
				//System.out.println(fileChooser.getSelectedFile().toString());
				comida= fileChooser.getSelectedFile().toString();
				//System.out.println(comida);
				if(alLinea.size() > 0){
					textArea.setText("");
					for (int iIndice = 0; iIndice < alLinea.size(); iIndice++){
						strTexto = (String) alLinea.get(iIndice);
						//System.out.println(strTexto);
						//analizador(strTexto);
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
		FileWriter f = null;
		try{
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Guardar Archivo");
			
			int returnVal = fc.showSaveDialog(Frame.this);
			

            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	f= new FileWriter (fc.getSelectedFile());
                String txta = textArea.getText();
				f.write(txta);
				f.close();
            } else {
            	JOptionPane.showMessageDialog (null, "Cancelado");
            }
		}catch(IOException ie){
			JOptionPane.showMessageDialog (null, "No se creo", "Error", JOptionPane.ERROR_MESSAGE);
			ie.printStackTrace();
		}
	}
	//Fin Funci�n GuardarComo
	
	//Inicio Funci�n Guardar
	void Guardar(){
		FileWriter f = null;
		try{
            if (comida != null) {
            	f= new FileWriter (comida);
                String txta = textArea.getText();
                //System.out.println(txta);
				f.write(txta);
				f.close();
				JOptionPane.showMessageDialog (null, "Guardado");
            } else {
            	//System.out.println("Ardilla");
            	GuardarComo();
            }
		}catch(IOException e){
			JOptionPane.showMessageDialog (null, "No se creo", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	//Fin Funci�n Guardar
	
	//Inicio Funcion Analizador
	void analizador(String strTexto){
		char charval[];
		charval = strTexto.toCharArray();
		String subusado = null;
		int x=0;
		for(x=1; x < strTexto.length(); x++ ){
			
			if(charval[x]==';'){
				subusado = strTexto.substring(0,x);
				//System.out.println("Encontro un ; !");
				//System.out.println("La cadena usada ser� : "+ subusado);
				x=strTexto.length();
				Cortador(subusado);
			}	
		}
		if (x==strTexto.length()){
			//System.out.println("La cadena usada ser� : "+ strTexto);
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
		int t2 = 0;
		
		String ORG = "ORG";
		String EQU = "EQU";
		String END = "END";
		
		//System.out.println("N�mero de Elemento: "+ cuentatokens);
		
		if (cuentatokens==3){
			Cod = tokens.nextToken();
			codver = Cod.toCharArray();
			if(Cod.length() > 8 || codver[0] < 'A' || codver[0] > 'z' ){
				Cod = ""; //Etiqueta con error
			}
			Instr = tokens.nextToken();
			Oper =  tokens.nextToken();
			Dirr = Busqueda (Instr);
			System.out.println("Direccionamientos de la Instruccion = "+Dirr);
			StringTokenizer countdir = new StringTokenizer(Dirr," ");
			int cuentadirs = countdir.countTokens();
			
			if(Instr.compareTo(ORG)!=0 && Instr.compareTo(EQU)!=0 && Instr.compareTo(END)!=0 && cuentadirs > 1)
			{
				System.out.println("Instruccion a coincidir = "+Instr);
				AnaOper (Oper);
			}
			else{
					System.out.println("Es REL, Buscando REL");
					Busqueda2 ("REL", Instr);
				
			}
		}
		if (cuentatokens==2){
			if(charval[0]==' '||charval[0]==';'){
				Cod = "";
				Instr = tokens.nextToken();
				Oper =  tokens.nextToken();
				t2=1;
				Dirr = Busqueda (Instr);
				System.out.println("Dir = "+Dirr);
				StringTokenizer countdir = new StringTokenizer(Dirr, " ");
				int cuentadirs = countdir.countTokens();
				System.out.println("Cuentadirs = "+cuentadirs);
				
				if(Instr.compareTo(ORG)!=0 && Instr.compareTo(EQU)!=0 && Instr.compareTo(END)!=0 && cuentadirs > 1)
				{
					System.out.println(Instr);
					AnaOper (Oper);
				}
				else{
					if (Instr.compareTo(ORG)==0){
						
					}
					else if (Instr.compareTo(EQU)==0){
						
					}
					else if (Instr.compareTo(END)==0){
						
					}
					else {
						System.out.println("Es REL");
					}
				}
			}
			else {
				Cod = tokens.nextToken();
				codver = Cod.toCharArray();
				if(Cod.length() > 8 || codver[0] < 'A' || codver[0] > 'z' ){
					Cod = ""; //Etiqueta con error
				}
				Instr = tokens.nextToken();
				Oper =  "";
				t2=1;
				Dirr = Busqueda (Instr);
				System.out.println("Dir = "+Dirr);
				StringTokenizer countdir = new StringTokenizer(Dirr, " ");
				int cuentadirs = countdir.countTokens();
				System.out.println("Cuentadirs = "+cuentadirs);
				
				if(Instr.compareTo(ORG)!=0 && Instr.compareTo(EQU)!=0 && Instr.compareTo(END)!=0 )
				{
					System.out.println("ES INH");//INH	
				}
				
			}
		}
		if (cuentatokens==1){
			Cod = "";
			Instr = tokens.nextToken();
			Oper = "";
			t2=1;
			Dirr = Busqueda (Instr);
		}
		if (cuentatokens==0){
			Cod = "";
			Instr = "";
			Oper = "";
			t2=1;
			Dirr = Busqueda (Instr);
		}
		agregarZapatos(Cod, Instr, Oper, Dirr);
	}
	//Fin Funcion SplitTokens
	
	//
	void agregarZapatos (String Cod, String Instr, String Oper, String Dirr) {
		lista.add(new Alojamiento (Cod, Instr, Oper, Dirr));
	}
	//
	
	//inicio Impresion
	void imprimir () {
		int nlinea =1;
		
		
		for (int i =0; i < lista.size(); i++, nlinea++ ) {
			System.out.println("\n*Linea "+ nlinea);	
			lista.get(i).imprimirVector(nlinea);
		}
	}
	//Fin Impresion
	
	//InicioBusqueda
	public String Busqueda(String Instr){
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
		String instrr = null;
		String diri = null;
		String direxx = "";
		String operi = null;
		String cbytes = null;
		int cb=0;
		try 
		{
			archivo = new File ("TABOP12.txt");
	        fr = new FileReader (archivo);
	        br = new BufferedReader(fr);
	        String linea;
	        while((linea=br.readLine())!=null){
	        	
	        	StringTokenizer instrsplit = new StringTokenizer(linea, "|");
	        	
	        		instrr = instrsplit.nextToken();
	        		diri = instrsplit.nextToken();
	        		operi = instrsplit.nextToken();
	        		cbytes  = instrsplit.nextToken();
	        		
	        		//cb = Integer.parseInt(cbytes, 10);
	        		//System.out.println("Se saco "+instrr+" y compara Con "+Instr);
	        		if (Instr.compareTo(instrr)==0 ){
	        			
	        			direxx = direxx+" "+diri;
	        		}
	        		
	        	//System.out.println("Final Direxx: "+ direxx);
	        }
	        
	            
		}catch (Exception ie)
		{
			ie.printStackTrace();
		}
		return direxx;
	}
	//Fin Busqueda
	
	
	//Inicio Busqueda2 
	void Busqueda2(String Instr, String III){
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
		String instrr = null;
		String diri = null;
		String direxx = "";
		String operi = null;
		String cbytes = null;
		int cb=0;
		try 
		{
			archivo = new File ("TABOP12.txt");
	        fr = new FileReader (archivo);
	        br = new BufferedReader(fr);
	        String linea;
	        System.out.println("Entro a B2 con Tipo Dir ="+Instr);
	        while((linea=br.readLine())!=null){
	        	
	        	StringTokenizer instrsplit = new StringTokenizer(linea, "|");
	        	
	        		instrr = instrsplit.nextToken();
	        		diri = instrsplit.nextToken();
	        		operi = instrsplit.nextToken();
	        		cbytes  = instrsplit.nextToken();
	        		
	        		//cb = Integer.parseInt(cbytes, 10);
	        		//System.out.println("Se saco "+instrr+" y compara Con "+Instr);
	        		if (III.compareTo(instrr)==0  ){
	        			if (Instr.compareTo(diri)==0  ){
		        			
		        			cb=Integer.parseInt(cbytes, 10);
		        			Clocd+=cb;
		        			System.out.println("Contador de Locas = "+Clocd);
		        			
		        		}
	        		}
	        		
	        		
	        	//System.out.println("Final Direxx: "+ direxx);
	        }
	        
	            
		}catch (Exception ie)
		{
			ie.printStackTrace();
		}
		
	}
	//Fin Busqueda2
	//Inicio Analizador Operando
	void AnaOper (String Oper){
		char opi[];
		StringTokenizer opersin = new StringTokenizer(Oper,"#$@%");
		String Acu=null;
		int codi = 0;
		int tip = 0;
		opi = Oper.toCharArray();
		
		if (opi[0]=='#'){
			System.out.println("GATO");
			
			 if (opi[1]=='$'){
				System.out.println("HEXA");
				Acu = opersin.nextToken();
				System.out.println(Acu);
				codi = Integer.parseInt(Acu,16);
				System.out.println("En Decimanl es = "+codi);
			}
			else if (opi[1]=='@'){
				System.out.println("OCTA");
				Acu = opersin.nextToken();
				System.out.println(Acu);
				codi = Integer.parseInt(Acu,8);
				System.out.println("En Decimal es = "+codi);
			}
			else if (opi[1]=='%'){
				System.out.println("BIN");
				Acu = opersin.nextToken();
				System.out.println(Acu);
				codi = Integer.parseInt(Acu,2);
				System.out.println("En Decimal es = "+codi);
			}
			else if (opi[1]>='0' || opi[0]<=9)
			{
				System.out.println("DEC");
				Acu = opersin.nextToken();
				System.out.println(Acu);
				codi = Integer.parseInt(Acu,10);
				System.out.println("En Decimanl es = "+codi);
			}
			tip=2;
			AnaCodi(codi , tip);
		}
		else if (opi[0]=='$'){
			System.out.println("HEXA");
			Acu = opersin.nextToken();
			System.out.println(Acu);
			codi = Integer.parseInt(Acu,16);
			System.out.println("El HEXA En Decimanl es = "+codi);
			tip=1;
			AnaCodi(codi , tip);
		}
		else if (opi[0]=='@'){
			System.out.println("OCTA");
			Acu = opersin.nextToken();
			System.out.println(Acu);
			codi = Integer.parseInt(Acu,16);
			System.out.println("El OCTA En Decimanl es = "+codi);
			tip=1;
			AnaCodi(codi , tip);
		}
		else if (opi[0]=='%'){
			System.out.println("BIN");
			Acu = opersin.nextToken();
			System.out.println(Acu);
			codi = Integer.parseInt(Acu,16);
			System.out.println("EL BIN En Decimanl es = "+codi);
			tip=1;
			AnaCodi(codi , tip);
		}
		else if (opi[0]>='0' || opi[0]<='9')
		{
			System.out.println("DEC");
			Acu = opersin.nextToken();
			System.out.println(Acu);
			codi = Integer.parseInt(Acu,10);
			System.out.println("EL DEC En Decimanl es = "+codi);
			tip=1;
			AnaCodi(codi , tip);
		}
		
	}
	//Fin 
	
	//Inicio AnaCodi
	void AnaCodi (int codi, int tip){
		if (tip == 1){ //Para INH DIR EXT
			if(codi >=0 && codi <= 255){
				System.out.println("ES DIR, Buscando DIR tipo2");//DIR
				Busqueda2 ("DIR", Instr);
			}
			else if(codi >255 && codi <= 65535){
				System.out.println("ES EXT, Buscando EXT tipo2");//EXT
				Busqueda2 ("EXT", Instr);
			}
		}
		if (tip == 2){ //Para INH DIR EXT
			System.out.println("ES INM, Buscando INM tipo2");//INM
			Busqueda2 ("INM", Instr);
		}
	}
}
