import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveUDP {

	private int port;

	public ReceiveUDP(int p) {
		this.port=p;
	}

	public void receive() {
			int port = this.port;
			int size = 1024;
			byte[] buf = new byte[size];
			try 
			{
			DatagramSocket ds = new DatagramSocket(port);
			DatagramPacket dp = new DatagramPacket(buf, size);
				ds.receive(dp);
				System.out.println(new String(buf));
				
			}
			catch (IOException e) {System.out.println(e);return;}
	}
}
