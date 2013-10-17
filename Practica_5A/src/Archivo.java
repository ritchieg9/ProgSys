import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class Archivo {
	
	Vector<String> alTexto = null;
	public Vector<String> getAlTexto() {
		return alTexto;
	}
	
	public void setAlTexto(Vector<String> alTexto) {
		this.alTexto = alTexto;
	}
	
	public Vector<String> LeeArchivo (String strFile){
		
		FileReader fr = null;
		BufferedReader br = null;
		File ffile = null;
		String strline = null;
		try
		{
			alTexto = new Vector<String> ();
			ffile = new File (strFile);
			if(ffile.exists())
			{
				if(ffile.isFile())
				{
					fr = new FileReader (ffile);
					br = new BufferedReader (fr);
					strline = br.readLine();
					while (strline != null ) {
						if(!strline.equalsIgnoreCase(""))
						{
							alTexto.add(strline);
						}
						strline = br.readLine();
					}
					br.close();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return alTexto;
	}
	
	public String GuardaArchivo(String GetText, JFileChooser fc, String Ruta){
		
		FileWriter f = null;
		try {
			if(Ruta!=null){
				f= new FileWriter (Ruta);
			}
			else{
				f= new FileWriter (fc.getSelectedFile());
				Ruta= fc.getSelectedFile().toString();
			}
			f.write(GetText);
			f.close();
			JOptionPane.showMessageDialog (null, "Guardado");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String RC = Ruta;
		return RC;
	}
}
