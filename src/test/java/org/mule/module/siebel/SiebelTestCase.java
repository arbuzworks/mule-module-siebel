/**
 * This file was automatically generated by the Mule Cloud Connector Development Kit
 */
package org.mule.module.siebel;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mule.api.lifecycle.InitialisationException;

import com.siebel.data.SiebelException;
import com.siebel.data.SiebelPropertySet;

public class SiebelTestCase {
	private static SiebelCloudConnector siebelConnector;

	@BeforeClass
	public static void initialise() throws IOException, InitialisationException {
		Properties properties = new Properties();

		InputStream propertiesFile = SiebelTestCase.class.getClassLoader()
				.getResourceAsStream("test.properties");
		properties.load(propertiesFile);

		String name = properties.getProperty("user");
		String password = properties.getProperty("password");
		String host = properties.getProperty("host");
		String port = properties.getProperty("port");
		String path = properties.getProperty("url_path");

		siebelConnector = new SiebelCloudConnector();
		siebelConnector.setUser(name);
		siebelConnector.setPassword(password);
		siebelConnector.setHost(host);
		siebelConnector.setPort(port);
		siebelConnector.setPath(path);

		siebelConnector.initialise();
	}

	@AfterClass
	public static void dispose() {
		if (siebelConnector != null) {
			siebelConnector.dispose();
			siebelConnector = null;
		}
	}

	@Test
	public void invokeEchoMethod() throws SiebelException {
		SiebelPropertySet propSetIn = new SiebelPropertySet();
		propSetIn.setValue("Please echo this");
		SiebelPropertySet result = siebelConnector.invokeMethod("Workflow Utilities", "Echo",
				propSetIn);
		assertEquals(propSetIn.getValue(), result.getValue());
	}
}