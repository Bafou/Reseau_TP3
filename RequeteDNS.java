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
		String[] splited = nom.split("\\.");
		Integer val;
		for (String part : splited) {
			size = part.length();
			val = new Integer(size);
			res[pos]= val.byteValue();
			pos++;
			byte[] subsequence= part.getBytes();
			for (int j=0;j<subsequence.length;j++) 
			{
				res[pos]=subsequence[j];
				pos++;
			}		
		}
		return res;
	}

	public byte[] headerDNS(){
		byte[] head = new byte[12];
		//identifiant		
		head[0] = 0;
		head[1] = 0;
		//parametre
		head[2] = 1;
		head[3] = 0;
		//question
		head[4] = 0;
		head[5] = 1;
		//reponse
		head[6] = 0;
		head[7] = 0;
		//autorite
		head[8] = 0;
		head[9] = 0;
		//info complementaire
		head[10] = 0;
		head[11] = 0;
		return head;
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
