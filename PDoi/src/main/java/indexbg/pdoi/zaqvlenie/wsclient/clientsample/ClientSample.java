package indexbg.pdoi.zaqvlenie.wsclient.clientsample;

import javax.xml.bind.JAXBException;

import indexbg.pdoi.zaqvlenie.wsclient.APIZaqvlenie;
import indexbg.pdoi.zaqvlenie.wsclient.APIZaqvlenieService;
import indexbg.pdoi.zaqvlenie.wsclient.Application;
import indexbg.pdoi.zaqvlenie.wsclient.WSFault;

public class ClientSample {

	public static void main(String[] args) throws JAXBException {
		try {
			System.out.println("***********************");
			System.out.println("Create Web Service Client...");
			APIZaqvlenieService service1 = new APIZaqvlenieService();
			System.out.println("Create Web Service...");
			APIZaqvlenie port1 = service1.getAPIZaqvleniePort();
			System.out.println("Call Web Service Operation...");
			System.out.println("Version: " + port1.version());

			System.out.println("SEARCHING FOR - 1");

			System.out.println("Server said: " + port1.findApplication("123456789"));

			// Please input the parameters instead of 'null' for the upper
			// method!

			System.out.println("Create Web Service...");
			APIZaqvlenie port2 = service1.getAPIZaqvleniePort();
			System.out.println("Call Web Service Operation...");
			System.out.println("Version: " + port2.version());

			System.out.println("SEARCHING FOR - 123456789");

			System.out.println("Server said: " + port2.findApplication("123456789"));
			// Please input the parameters instead of 'null' for the upper
			// method!
			Application ap = port2.findApplication("123456789");

		} catch (WSFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
