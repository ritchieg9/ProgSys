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
	Cadenatxt analizador = new Cadenatxt ();
	
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
				if(gtext.compareTo("")!=0){
					manejo(gtext);
				}
				
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
		StringTokenizer clinea = new StringTokenizer (gtext, "\n");
		Cadena Ccadena = new Cadena();
		
		String Etiqueta = "";
		String Instruccion = "";
		String Operando = "";
		String Direccionamiento = "";
		String CodigoMaquina = "";
		String clocal1 = "0000";
		String clocal2 = "0000";
		
		String linea = "";
		String lorden[] = null;
		int vf = 0;
		
		while(clinea.hasMoreTokens()){
			linea = clinea.nextToken();
			linea = Ccadena.DeleteComment (linea);
			vf = Ccadena.Revisadir(linea); // Revisando que no tenga 1:END 2:ORG 3:EQU D: 
			
			if(vf == 1){
				//END
				//System.out.println(linea);
				break;
			}
			else if(vf == 2){
				//ORG
			}
			else if(vf == 3){
				//EQU
			}
			else {
				//System.out.println(linea);
				lorden = Ccadena.Segmentos(linea);
				System.out.println("ETQ: "+lorden[0]+" INS: "+lorden[1]+" OPE: "+lorden[2]);
				if(lorden[2].compareTo("")!=0){
					Direccionamiento = Ccadena.AnalizarOperando (lorden[2]);
				}
				
			}
		}
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
			
		} catch(NumberFormatException nfe) {
			   System.out.println(nfe.getMessage());
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
	//Fin Funci�n GuardarComo
	
	//Inicio Funci�n Guardar
	void Guardar(){
		if (RutaOpen != null) {
			Archivo guardar = new Archivo ();
			String txta = textArea.getText();
			RutaOpen =guardar.GuardaArchivo(txta, fc, RutaOpen);
		} else {
			GuardarComo();
		}
	}
}