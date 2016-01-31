package storm.helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EchoClient {
	
	private String value;
	private Socket echoSocket = null;
	private BufferedReader in = null;
	private BufferedReader stdIn = null;
	
	public void start() throws IOException {
		echoSocket = new Socket("localhost", 9999);
		in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		stdIn = new BufferedReader(new InputStreamReader(System.in));
    }
	
	public void read() throws IOException {
		char[] buffer = new char[2048];
		int charsRead = 0;
		charsRead = in.read(buffer);
		String message = new String(buffer).substring(0, charsRead);
		value = message;
	}
	
	public void close() throws IOException {
		echoSocket.close();
		in.close();
		stdIn.close();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
