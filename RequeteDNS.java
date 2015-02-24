import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class RequeteDNS {
	
	//Use to test the StringToLabel
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}

	public static byte[] StringToLabel (String nom) {
		//the result of the method
		byte[] res= new byte[nom.length()+1] ;
		//pos is the position on the byte array
		int pos=0,size;
		//the different part of the label
		String[] splited = nom.split(".");
		System.out.println(splited.length);
		Integer val;
		for (int i=0;i<splited.length;i++) {
			size = splited[i].length();
			System.out.println(size);
			val = new Integer(size);
			res[pos]= val.byteValue();
			byte[] subsequence= splited[i].getBytes();
			for (int j=0;j<subsequence.length;j++) 
			{
				pos++;
				res[pos]=subsequence[j];
			}		
		}
		return res;
	}
	
	 public static void main (String argv[]) {
		 if (argv.length == 1) {
		 /*int port = 53;
		 byte[] buf = StringToLabel(argv[0]);
		 InetAddress addr = InetAddress.getByName("172.18.12.9");
		 */
		byte[] buf = StringToLabel(argv[0]);
		System.out.println(bytesToHex(buf));
		 }
		 else System.out.println("Erreur écrire sous la forme : java RequeteDNS <nom>");
	 }
}
