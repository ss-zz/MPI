package com.sinosoft.pixpdqv3.iti47;

import com.sinosoft.pixpdqv3.Hl7v3ContinuationsPortType;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;

@WebService(targetNamespace = "urn:ihe:iti:pdqv3:2007", name = "PDSupplier_PortType")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface Iti47PortType extends Hl7v3ContinuationsPortType {

	@WebResult(name = "PRPA_IN201306UV02", targetNamespace = "urn:hl7-org:v3", partName = "Body")
	@Action(input = "urn:hl7-org:v3:PRPA_IN201305UV02", output = "urn:hl7-org:v3:PRPA_IN201306UV02")
	@WebMethod(operationName = "PDSupplier_PRPA_IN201305UV02", action = "urn:hl7-org:v3:PRPA_IN201305UV02")
	public String operation(
			@WebParam(partName = "Body", name = "PRPA_IN201305UV02", targetNamespace = "urn:hl7-org:v3") String request);

	@WebResult(name = "PRPA_IN201306UV02", targetNamespace = "urn:hl7-org:v3", partName = "Body")
	@Action(input = "urn:hl7-org:v3:QUQI_IN000003UV01_Continue", output = "urn:hl7-org:v3:PRPA_IN201306UV02")
	@WebMethod(operationName = "PDSupplier_QUQI_IN000003UV01_Continue", action = "urn:hl7-org:v3:PDSupplier_QUQI_IN000003UV01_Continue")
	public String continuation(
			@WebParam(partName = "Body", name = "QUQI_IN000003UV01", targetNamespace = "urn:hl7-org:v3") String request);

	@WebResult(name = "MCCI_IN000002UV01", targetNamespace = "urn:hl7-org:v3", partName = "Body")
	@Action(input = "urn:hl7-org:v3:QUQI_IN000003UV01_Cancel", output = "urn:hl7-org:v3:MCCI_IN000002UV01")
	@WebMethod(operationName = "PDSupplier_QUQI_IN000003UV01_Cancel", action = "urn:hl7-org:v3:QUQI_IN000003UV01_Cancel")
	public String cancel(
			@WebParam(partName = "Body", name = "QUQI_IN000003UV01_Cancel", targetNamespace = "urn:hl7-org:v3") String request);
}
