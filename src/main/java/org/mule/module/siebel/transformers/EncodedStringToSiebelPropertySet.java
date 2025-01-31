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
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.mule.transformer.types.DataTypeFactory;

import com.siebel.data.SiebelPropertySet;

/**
 * Transformer to convert encoded string to SiebelPropertySet object
 */
public class EncodedStringToSiebelPropertySet extends AbstractMessageTransformer implements DiscoverableTransformer {

	private int weighting = DiscoverableTransformer.DEFAULT_PRIORITY_WEIGHTING;

	/**
	 * Get a priority weighting
	 * @return
	 */
	public int getPriorityWeighting() {
		return weighting;
	}

	/**
	 * Specify the priority weighting
	 * @param weighting
	 */
	public void setPriorityWeighting(int weighting) {
		this.weighting = weighting;
	}

	public EncodedStringToSiebelPropertySet() {
		registerSourceType(DataTypeFactory.TEXT_STRING);
		setReturnDataType(DataTypeFactory.create(SiebelPropertySet.class));
		setName(EncodedStringToSiebelPropertySet.class.getSimpleName());
	}

	/** {@inheritDoc}
	 */
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		SiebelPropertySet result = new SiebelPropertySet((String) message.getPayload());
		return result;
	}

}
