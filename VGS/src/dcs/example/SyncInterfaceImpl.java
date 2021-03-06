package dcs.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SyncInterfaceImpl extends UnicastRemoteObject implements SyncInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Construct the object that implements in remote server*/
	public SyncInterfaceImpl() throws RemoteException{
		super();	//Set up networking
	}
	
	/** Implement the function client will call*/
	public int getStatus() throws RemoteException{
		//return the value of queue size	
		try {
			return 10;
		} catch (Exception e) {
			System.out.print("ServerNode::Connect to server but there is an exception "+ e);
			return 0;
		}
	}
}
