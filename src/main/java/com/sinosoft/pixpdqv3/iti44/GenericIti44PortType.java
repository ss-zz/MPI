package com.sinosoft.pixpdqv3.iti44;

public interface GenericIti44PortType {
	String recordAdded(String request);

	String recordRevised(String request);

	String duplicatesResolved(String request);
}
