import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class RequeteDNS {

	public static byte[] StringToLabel (String nom) {
		//the result of the method
		byte[] res= new byte[nom.length()+1] ;
		//pos is the position on the byte array
		int pos=0,size;
		//the different part of the label
		String[] splited = nom.split(".");
		Integer val;
		for (int i=0;i<splited.length;i++) {
			size = splited[i].length();
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
		System.out.println(Arrays.toString(buf));
		 }
		 else System.out.println("Erreur écrire sous la forme : java RequeteDNS <nom>");
	 }
}
