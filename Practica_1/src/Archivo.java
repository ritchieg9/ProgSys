import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;


public class Archivo {
	
	public Vector<String> LeeArchivo (String strFile){
		Vector<String> alTexto = null;
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
					//alTexto.add(strline);
					strline = br.readLine();
					while (strline != null ) {
						if(!strline.equalsIgnoreCase(""))
						{
							//System.out.println(strline);
							alTexto.add(strline);
							//strline = br.readLine();
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
	
}
