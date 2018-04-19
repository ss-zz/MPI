package com.sinosoft.pixpdqv3.iti46;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;

@WebService(targetNamespace = "urn:ihe:iti:pixv3:2007", name = "PIXConsumer_PortType")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface Iti46PortType {

	@WebResult(name = "MCCI_IN000002UV01", targetNamespace = "urn:hl7-org:v3", partName = "Body")
	@Action(input = "urn:hl7-org:v3:PRPA_IN201302UV02", output = "urn:hl7-org:v3:MCCI_IN000002UV01")
	@WebMethod(operationName = "PIXConsumer_PRPA_IN201302UV02", action = "urn:hl7-org:v3:PRPA_IN201302UV02")
	public String recordRevised(
			@WebParam(partName = "Body", name = "PRPA_IN201302UV02", targetNamespace = "urn:hl7-org:v3") String request);
}
