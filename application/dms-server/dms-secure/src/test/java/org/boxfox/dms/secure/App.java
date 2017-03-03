package org.boxfox.dms.secure;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.boxfox.dms.algorithm.RSA;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	System.out.println();
    	ServerSocket soc = new ServerSocket(25565);
    	System.out.println("wait");
    	Socket client = soc.accept();
    	System.out.println("conn");
    	DataOutputStream dout = new DataOutputStream(client.getOutputStream());
    	DataInputStream din = new DataInputStream(client.getInputStream());
    	dout.writeUTF(RSA.PUBLIC_KEY);
    	System.out.println(RSA.decrypt(din.readUTF()));
    }
}
