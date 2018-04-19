package com.sinosoft.pixpdqv3;

/**
 * Generic Web Service port type for HL7 v3-based IHE components with
 * continuations support.
 * 
 * @author Dmytro Rud
 */
public interface Hl7v3ContinuationsPortType {

	String operation(String request);

	String continuation(String request);

	String cancel(String request);

}
