import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/**
 * @author Antoine PETIT
 */
public class RequeteDNS extends Thread {
	
	protected int port;
	protected String request;
	
	public RequeteDNS (int p,String req) {
		this.port = p;
		this.request=req;
	}
	
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

	public static byte[] headerDNS(){
		byte[] head = new byte[12];
		byte[] rand = new byte[2];
		new Random().nextBytes(rand);
		//identifiant		
		head[0] = rand[0];
		head[1] = rand[1];
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

	public static byte[] footerDNS(){
		byte[] foot = new byte[5];
		//end of name		
		foot[0] = 0;
		//type
		foot[1] = 0;
		foot[2] = 1;
		//class
		foot[3] = 0;
		foot[4] = 1;
		return foot;
	}

	public void run () {
		 
		byte[] buf = StringToLabel(this.request);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			outputStream.write(headerDNS());
			outputStream.write(buf);
			outputStream.write(footerDNS());
		} catch (IOException e) {
			System.out.println(e);
			return;
		}

		byte[] res = outputStream.toByteArray();
		System.out.println (bytesToHex(res));
		try {
			InetAddress addr = InetAddress.getByName("");
			DatagramPacket dp = new DatagramPacket (buf,buf.length, addr,53);
			DatagramSocket ds= new DatagramSocket(this.port);
			ds.connect(addr,53);
			ds.send(dp);
			ds.disconnect();
			ds.close();
		}
		catch (IOException e) {
			System.out.println(e);
			return;
		}
			
	 }
}
