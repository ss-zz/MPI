package com.sinosoft.pixpdqv3.iti44;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;

/**
 * ITI-44 port type for XDS.
 */
@WebService(targetNamespace = "urn:ihe:iti:xds-b:2007", name = "DocumentRegistry_PortType")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface Iti44XdsPortType extends GenericIti44PortType {

	@WebResult(name = "MCCI_IN000002UV01", targetNamespace = "urn:hl7-org:v3", partName = "Body")
	@Action(input = "urn:hl7-org:v3:PRPA_IN201301UV02", output = "urn:hl7-org:v3:MCCI_IN000002UV01")
	@WebMethod(operationName = "DocumentRegistry_PRPA_IN201301UV02", action = "urn:hl7-org:v3:PRPA_IN201301UV02")
	String recordAdded(
			@WebParam(partName = "Body", name = "PRPA_IN201301UV02", targetNamespace = "urn:hl7-org:v3") String request);

	@WebResult(name = "MCCI_IN000002UV01", targetNamespace = "urn:hl7-org:v3", partName = "Body")
	@Action(input = "urn:hl7-org:v3:PRPA_IN201302UV02", output = "urn:hl7-org:v3:MCCI_IN000002UV01")
	@WebMethod(operationName = "DocumentRegistry_PRPA_IN201302UV02", action = "urn:hl7-org:v3:PRPA_IN201302UV02")
	String recordRevised(
			@WebParam(partName = "Body", name = "PRPA_IN201302UV02", targetNamespace = "urn:hl7-org:v3") String request);

	@WebResult(name = "MCCI_IN000002UV01", targetNamespace = "urn:hl7-org:v3", partName = "Body")
	@Action(input = "urn:hl7-org:v3:PRPA_IN201304UV02", output = "urn:hl7-org:v3:MCCI_IN000002UV01")
	@WebMethod(operationName = "DocumentRegistry_PRPA_IN201304UV02", action = "urn:hl7-org:v3:PRPA_IN201304UV02")
	String duplicatesResolved(
			@WebParam(partName = "Body", name = "PRPA_IN201304UV02", targetNamespace = "urn:hl7-org:v3") String request);
}
