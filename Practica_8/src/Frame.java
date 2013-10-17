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
	int vuelta = 1;
	String Instr= null;
	String Oper= null;
	String Nul= null;
	String Dirr = null;
	String Cloc = null;
	String Acu=null;
	String LOCAN = "0000";
	String LOCA = "0000";
	String operi = "";
	String xopero = "";
	int xrel = 1;
	//int Clocd = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	FileNameExtensionFilter filter = new FileNameExtensionFilter (".txt and .asm files", "txt", "asm");
	private JTextField textField;
	String RutaOpen = null;
	private JTextArea textArea;
	
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
				String gtext = textArea.getText();
				manejo(gtext);
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
	
	void manejo(String gtext){
		System.out.println("Vuelta: "+vuelta);
		vuelta++;
		String cadena = "";
		String combo[] = null; //ETQ+INST+OPER+TYPE
		String CAL = "0000";
		String CL = "00";
		String TBS = "";
		int scl =0;
		String ETQ = "";
		String INS = "";
		String OPE = "";
		String DIR = "";
		String ACU = "";
		String COL = "";
		String COB = "";
		
		StringTokenizer cutxt = new StringTokenizer (gtext, "\n");
		while(cutxt.hasMoreTokens()){
			Cadenatxt analizador = new Cadenatxt ();
			cadena = analizador.DeleteComment (cutxt.nextToken()); //Quitar Comentario Si es que lo trae.
			combo = analizador.Cortador(cadena, CAL);
			int cln =0;
			int cl = 0;

			ETQ = "";
			INS = "";
			OPE = "";
			DIR = "";
			ACU = "";
			COL = "";
			COB = "";
			
			Acu = Dirr;
			if (combo[1].compareTo("ORG")==0 ){
				
				//System.out.println("ETQ "+combo[0]+"\t\t INST "+combo[1]+"\t\tOPER "+combo[2]+"\t\tDIR "+combo[3]+"\t\tACU "+combo[4]+"\t CL "+combo[5]+"CB "+combo[6]);
				System.out.println(combo[5]+"\t\t "+combo[4]+"\t\t "+combo[0]+"\t\t "+combo[1]+"\t\t "+combo[2]);
				
				while(combo[5].length()<4){
					combo[5]="0"+combo[5];
				}
				CL = combo[5];
				
				
				ETQ = combo[0];
				INS = combo[1];
				OPE = combo[2];
				DIR = combo[3];
				ACU = combo[4];
				COL = combo[5];
				COB = combo[6];
			}
			else if (combo[1].compareTo("EQU")==0){
				//System.out.println("ETQ "+combo[0]+"\t\t INST "+combo[1]+"\t\tOPER "+combo[2]+"\t\tDIR "+combo[3]+"\t\tACU "+combo[4]+"\t CL "+combo[5]+"CB "+combo[6]);
				System.out.println(combo[5]+"\t\t "+combo[4]+"\t\t "+combo[0]+"\t\t "+combo[1]+"\t\t "+combo[2]);
				
				while(combo[5].length()<4){
					combo[5]="0"+combo[5];
				}
				TBS += combo[0]+"\t"+combo[5]+"\n";
				ETQ = combo[0];
				INS = combo[1];
				OPE = combo[2];
				DIR = combo[3];
				ACU = combo[4];
				COL = combo[5];
				COB = combo[6];
			}
			
			else if (combo[1].length()!=0){

				//System.out.println("ETQ "+combo[0]+"\t\t INST "+combo[1]+"\t\tOPER "+combo[2]+"\t\tDIR "+combo[3]+"\t\tACU "+combo[4]+"\t CL "+CL+" CB "+combo[6]);
				System.out.println(CL+"\t\t "+combo[4]+"\t\t "+combo[0]+"\t\t "+combo[1]+"\t\t "+combo[2]);
				
				if(combo[0].length()>0 && combo[1].compareTo("EQU")!=0){
					
					while(CL.length()<4){
						CL="0"+CL;
					}
					TBS += combo[0]+"\t"+CL+"\n";
					
					
				}
				ETQ = combo[0];
				INS = combo[1];
				OPE = combo[2];
				DIR = combo[3];
				ACU = combo[4];
				COL = CL;
				COB = combo[6];
				if(combo[5].compareTo("")!=0)
				cl = Integer.parseInt(combo[5],16);
				cln = Integer.parseInt(CL,16);
				scl = cl + cln;
				CL = Integer.toString(scl,16);
				while(CL.length()<4){
					CL="0"+CL;
				}
				
			}
			agregarZapatos (ETQ, INS, OPE, DIR, ACU, COL, COB);
			
		}
		
		
		System.out.println("\n\n\tTBS \n"+TBS);
		Archivo lstx = new Archivo ();
		lstx.GuardaArchivo(TBS, fc, "Prueba.tbs");
		imprimir();
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
		}catch(NumberFormatException ex){
        	System.out.println(ex.getMessage());
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
		//ETQ, INS, OPE, DIR, ACU, COL, COB
		void agregarZapatos (String Etiqueta, String Instr, String Oper, String Dirr, String Acuu, String CLocal, String Conb) {
			lista.add(new Alojamiento (Etiqueta, Instr, Oper, Dirr, Acuu, CLocal, Conb ));
		}
		//
	
	//inicio Impresion
	void imprimir () {
		int nlinea =1;
		String lst= "";
		for (int i =0; i < lista.size(); i++, nlinea++ ) {
			System.out.println("\n*Linea "+ nlinea);	
			lst += lista.get(i).imprimirVector(nlinea)+"\n";
		}
		System.out.println("\n\n"+lst);
		Archivo guardar = new Archivo ();
		RutaOpen =guardar.GuardaArchivo(lst, fc, "Prueba.lst");
	}
	//Fin Impresion
	
}