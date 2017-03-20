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

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * The type MapperDefault
 *
 * @author Peter Pilgrim (peter)
 */
@Singleton
public class MapperDefault implements Mapper {

    @Inject
    private Normalizer normalizer;


    @Override
    public void mapXmlPropertiesToJobRecord(JobConfigurationPropertiesLoader configuration, XMLStreamReader streamReader, JobRecord jobRecord) throws XMLStreamException {
        final String localName = streamReader.getLocalName();
        if (configuration.getTitleElementName().equalsIgnoreCase(localName)) {
            jobRecord.setTitle(normalizer.notNullAndNormalise(streamReader.getElementText()));
        }
        if (configuration.getDescriptionElementName().equalsIgnoreCase(localName)) {
            jobRecord.setDescription(normalizer.notNullAndNormalise(streamReader.getElementText()));
        }
        if (configuration.getLocationElementName().equalsIgnoreCase(localName)) {
            jobRecord.setLocation(normalizer.notNullAndNormalise(streamReader.getElementText()));
        }
        if (configuration.getReferenceElementName().equalsIgnoreCase(localName)) {
            jobRecord.setReference(normalizer.notNullAndNormalise(streamReader.getElementText()));
        }
        if (configuration.getUrlElementName().equalsIgnoreCase(localName)) {
            jobRecord.setUrl(normalizer.notNullAndNormalise(streamReader.getElementText()));
        }
    }

    @Override
    public Normalizer getNormalizer() {
        return normalizer;
    }

    @Override
    public void setNormalizer(Normalizer normalizer) {
        this.normalizer = normalizer;
    }

}
