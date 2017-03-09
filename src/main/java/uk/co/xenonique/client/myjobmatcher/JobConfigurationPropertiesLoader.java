/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2017 by Peter Pilgrim, Milton Keynes, P.E.A.T UK LTD
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Creative Commons 3.0
 * Non Commercial Non Derivation Share-alike License
 * https://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 * Developers:
 * Peter Pilgrim -- design, development and implementation
 *               -- Blog: http://www.xenonique.co.uk/blog/
 *               -- Twitter: @peter_pilgrim
 *
 * Contributors:
 *
 *******************************************************************************/
package uk.co.xenonique.client.myjobmatcher;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type JobConfigurationPropertiesLoader
 *
 * @author Peter Pilgrim (peter)
 */
public class JobConfigurationPropertiesLoader {

    private String sentinelNodeElementName;

    private String titleElementName;
    private String descriptionElementName;
    private String locationElementName;
    private String referenceElementName;
    private String urlElementName;


    public void load(InputStream is) throws IOException {

        final Properties properties = new Properties();
        properties.load(is);
        sentinelNodeElementName = properties.getProperty("sentinelNode", "__UNKNOWN__");
        titleElementName = properties.getProperty("title", "title");
        descriptionElementName = properties.getProperty("description", "description");
        locationElementName = properties.getProperty("location", "location");
        referenceElementName = properties.getProperty("reference", "reference");
        urlElementName = properties.getProperty("url", "url");
    }

    public String getSentinelNodeElementName() {
        return sentinelNodeElementName;
    }


    public String getTitleElementName() {
        return titleElementName;
    }

    public String getDescriptionElementName() {
        return descriptionElementName;
    }

    public String getLocationElementName() {
        return locationElementName;
    }

    public String getReferenceElementName() {
        return referenceElementName;
    }

    public String getUrlElementName() {
        return urlElementName;
    }
}
