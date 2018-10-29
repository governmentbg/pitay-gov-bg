package indexbg.pdoi.wsclient.admreg.clientsample;

import indexbg.pdoi.wsclient.admreg.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        BatchInfoService service1 = new BatchInfoService();
	        System.out.println("Create Web Service...");
	        IBatchInfoService port1 = service1.getWSHttpBindingIBatchInfoService();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.getBatchDetailedInfo(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.searchBatchVersions(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getTerritorialAdmStructure(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.searchBatchesIdentificationInfo(null,null,null,null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Create Web Service...");
	        IBatchInfoService port2 = service1.getWSHttpBindingIBatchInfoService();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.getBatchDetailedInfo(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.searchBatchVersions(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getTerritorialAdmStructure(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.searchBatchesIdentificationInfo(null,null,null,null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
