/*******************************************************************************
 * Copyright (c) 2014 ITpearls, AG
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ITpearls AG - Stardust Runtime Extension
 *
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application;

public enum ApplicationTypes {

	WEBSERVICE("webservice", "Webservice"),
	PLAINJAVA("plainJava", "Plain Java"),
	SPRINGBEAN("springBean", "Spring Bean"),
	SESSIONBEAN("sessionBean", "Session Bean"),
	CAMELCONSUMER("camelConsumerApplication", "Camel Consumer"),
	CAMELPRODUCER_SEND("camelSpringProducerApplication", "Camel Producer (send)"),
	CAMELPRODUCER_SENDRECEIVE("camelSpringProducerApplicationSendReceive", "Camel Producer (send/receive)"),
	JMS("jms", "JMS Application"),
	EXTERNAL_WEBAPP("externalWebApp", "External Web Application")
	;

	private String key;
	public String displayName;

	private ApplicationTypes(String key, String displayName) {
		this.setKey(key);
		this.displayName = displayName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static ApplicationTypes forKey(String key) {
		if (null == key) return null;
		for (ApplicationTypes t : values()) {
			if (key.equals(t.key)) return t;
		}
		return null;
	}

}
