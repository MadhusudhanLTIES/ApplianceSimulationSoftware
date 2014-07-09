package Communication;

import Common.Constants;
import DTO.Appliance;

public class ArrayentBackEndCommunication
{
	public Appliance _applInstance;
	
	Process _client, _daemon;
	Constants _constantobj;
	
	Arrayent _arrayentclientConnector;
	
	public ArrayentBackEndCommunication(Appliance app)
	{
		this._applInstance=app;
		_constantobj = new Constants();
		_arrayentclientConnector= new Arrayent();
	}
	
	public Boolean CreateArrayentChannel()
	{
		if(CreateDaemonProcess())
		{
			_arrayentclientConnector.Connect(_constantobj.SERVER_IP_ADDRESS);
			return true;
		}
		else
		{
			KillProcess();
			return false;
		}
	}
	
	private Boolean CreateDaemonProcess()
	{
		try
		{
			_daemon = new ProcessBuilder(_constantobj.ARRAYENT_DAEMON_PATH,"-I",_applInstance.get_said(),"--no-login").start();
			_client = new ProcessBuilder(_constantobj.ARRAYENT_CLIENT_PATH,_applInstance.get_said(),_applInstance.get_password(),_applInstance.get_aesKey(),"11").start();
			return true;
		}
		catch(Exception exp)
		{
			return false;
		}
	}
		
	public Boolean KillProcess()
	{
		if(_client!=null)
			_client.destroy();
		
		if(_daemon!=null)
			_daemon.destroy();
		
		if(_arrayentclientConnector!=null)
			_arrayentclientConnector.Disconnect();
		return true;
	}
}
