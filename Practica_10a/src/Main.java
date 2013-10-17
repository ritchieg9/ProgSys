
public class Main {
	public static void main(String[] args) {
		String test = "A";
	     for ( int i = 0; i < test.length(); ++i ) {
	       char c = test.charAt( i );
	       int j = (int) c;
	       String asciic = Integer.toHexString(j);
	       System.out.println(asciic);
	       }
	}
}
