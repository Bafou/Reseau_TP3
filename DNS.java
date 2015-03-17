/**
 * 
 * @author Antoine PETIT
 *
 */
public class DNS {
	
	public static void main (String[] args) {
		
		RequeteDNS rdns = new RequeteDNS(4280, args[0]);
		ReceiveUDP ru = new ReceiveUDP(4280);
		rdns.send();
		System.out.println("Message envoyé");
		ru.receive();
		
	}
}
