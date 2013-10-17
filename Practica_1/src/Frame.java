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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JTextField;


public class Frame extends JFrame {

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
			if (iResp == JFileChooser.APPROVE_OPTION){
				
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
						textArea.append(strTexto + "\n");
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
	//Fin Función GuardarComo
	
	//Inicio Función Guardar
	void Guardar(){
		FileWriter f = null;
		try{
            if (comida != null) {
            	f= new FileWriter (comida);
                String txta = textArea.getText();
                System.out.println(txta);
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
	//Fin Función Guardar
	
}
