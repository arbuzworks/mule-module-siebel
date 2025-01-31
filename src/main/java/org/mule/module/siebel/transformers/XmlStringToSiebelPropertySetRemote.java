/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.siebel.transformers;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.module.siebel.SiebelCloudConnector;
import org.mule.transformer.types.DataTypeFactory;

import com.siebel.data.SiebelPropertySet;

/**
 * Transformer to convert XML string to SiebelPropertySet object using remote
 * siebel XML converter
 */
public class XmlStringToSiebelPropertySetRemote extends
		AbstractSiebelRemoteTransformer {

	public XmlStringToSiebelPropertySetRemote() {
		registerSourceType(DataTypeFactory.XML_STRING);
		setReturnDataType(DataTypeFactory.create(SiebelPropertySet.class));
		setName(XmlStringToSiebelPropertySetRemote.class.getSimpleName());
	}

	/** {@inheritDoc}
	 */
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		SiebelPropertySet result;
		try {
			String xml = (String) message.getPayload();
			SiebelPropertySet in = new SiebelPropertySet();
			in.setByteValue(xml.getBytes("UTF-8"));
			SiebelCloudConnector connector = getSiebelConnector();
			result = connector.invokeMethod(XML_CONVERTER, "XMLToPropSet", in);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new TransformerException(this, e);
		}
		return result;
	}

}
