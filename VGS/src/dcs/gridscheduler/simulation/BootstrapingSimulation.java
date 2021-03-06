package dcs.gridscheduler.simulation;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import dcs.gridscheduler.model.ConfigurationReader;
import dcs.gridscheduler.model.DistributedServer;
import dcs.gridscheduler.model.ServerURL;
import dcs.gridscheduler.model.SyncServerInterface;

public class BootstrapingSimulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//get id from console 
		int id = (args.length < 1)? 0 :Integer.valueOf(args[0]);
		
		// Hard code server -- can change to reading from configuration file
		// new format of server //host:port/name
		// ServerList
		//Configuration file 
		String path = "C:\\ec2\\deployment-vdo\\Github\\VGS\\ServerList.csv";
		ConfigurationReader config = new ConfigurationReader(path);
		ServerURL[] serverURLList = config.URLparsing();

		DistributedServer GSNode = null;
		// Starting the instance of server
		try {
			GSNode = new DistributedServer(id, serverURLList);
			GSNode.starting();
			//GSNode is suddenly crashed previous time --> 
			GSNode.setPreviousState(false);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Timer waitingForOtherGSNode = new Timer();
		waitingForOtherGSNode.schedule(new ConnectTimerTask(GSNode,serverURLList),20000);// Waiting for 20s 
	}

}
