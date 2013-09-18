/**
 * This file was automatically generated by the Mule Cloud Connector Development Kit
 */
package org.mule.module.siebel.config;

import org.mule.api.MuleEvent;
import org.mule.construct.SimpleFlowConstruct;
import org.mule.module.siebel.transformers.SiebelPropertySetToEncodedStringTestCase;
import org.mule.tck.FunctionalTestCase;

import com.siebel.data.SiebelPropertySet;

public class SiebelEncodedStringTestCase extends FunctionalTestCase {
	
	@Override
	protected String getConfigResources() {
		return "siebel-encoded-string-config.xml";
	}

	private SimpleFlowConstruct lookupFlowConstruct(String name) {
		return (SimpleFlowConstruct) muleContext.getRegistry().lookupFlowConstruct(name);
	}
	
    public void testEncodedString() throws Exception
    {
    	SiebelPropertySetToEncodedStringTestCase tc = new SiebelPropertySetToEncodedStringTestCase();
    	String str = (String)tc.getResultData();
    	
		SimpleFlowConstruct flow = lookupFlowConstruct("encodedStringFlow");
		MuleEvent event = getTestEvent(str);
		MuleEvent responseEvent = flow.process(event);

		SiebelPropertySet result = responseEvent.getMessage().getPayload(SiebelPropertySet.class);
		assertTrue(str.equals(result.encodeAsString()));
    }
}
