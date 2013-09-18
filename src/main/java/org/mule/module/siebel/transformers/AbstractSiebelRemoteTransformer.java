/*
 * $Id: $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.module.siebel.transformers;

import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.module.siebel.SiebelCloudConnector;
import org.mule.transformer.AbstractMessageTransformer;

/**
 * Abstract class for Siebel remote transformer 
 *
 */
public abstract class AbstractSiebelRemoteTransformer extends
		AbstractMessageTransformer implements DiscoverableTransformer {
	
	protected static final String XML_CONVERTER = "XML Converter";
	private SiebelCloudConnector siebelConnector;
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

	/**
	 * Get a siebel connector
	 * @return
	 */
	public SiebelCloudConnector getSiebelConnector()
			throws TransformerException {
		return siebelConnector;
	}

	/**
	 * Specify the siebel connector
	 * @param connector
	 */
	public void setSiebelConnector(SiebelCloudConnector siebelConnector) {
		this.siebelConnector = siebelConnector;
	}
}
