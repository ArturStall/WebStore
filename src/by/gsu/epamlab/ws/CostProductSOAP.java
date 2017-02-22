package by.gsu.epamlab.ws;

public class CostProductSOAP {
	
	public int getCost(int idProduct) {
		SOAPImplService implService = new SOAPImplService();
		SOAP service = implService.getSOAPImplPort();
		return service.getCost(idProduct);
	}
	
}