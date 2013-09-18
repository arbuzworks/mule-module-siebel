/**
 * This file was automatically generated by the Mule Cloud Connector Development Kit
 */
package org.mule.module.siebel.config;

import org.mule.api.MuleEvent;
import org.mule.construct.SimpleFlowConstruct;
import org.mule.module.siebel.transformers.SiebelPropertySetToXmlStringTestCase;
import org.mule.tck.FunctionalTestCase;

import com.siebel.data.SiebelPropertySet;

public class SiebelLocalXmlTestCase extends FunctionalTestCase {
	
	@Override
	protected String getConfigResources() {
		return "siebel-local-xml-config.xml";
	}

	private SimpleFlowConstruct lookupFlowConstruct(String name) {
		return (SimpleFlowConstruct) muleContext.getRegistry().lookupFlowConstruct(name);
	}
	
    public void testLocalXml() throws Exception
    {
    	SiebelPropertySetToXmlStringTestCase tc = new SiebelPropertySetToXmlStringTestCase();
    	String str = (String)tc.getResultData();
    	
		SimpleFlowConstruct flow = lookupFlowConstruct("localXmlFlow");
		MuleEvent event = getTestEvent(str);
		MuleEvent responseEvent = flow.process(event);

		SiebelPropertySet result = responseEvent.getMessage().getPayload(SiebelPropertySet.class);
		assertTrue(tc.compareRoundtripResults(tc.getTestData(), result));
    }
}
