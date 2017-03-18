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
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * The type JobBundleReaderDefault
 *
 * @author Peter Pilgrim (peter)
 */
public class JobBundleReaderDefault implements JobBundleReader {

    @Inject
    private Mapper mapper;

    @Override
    public List<JobRecord> load(final JobConfigurationPropertiesLoader configuration, final InputStream is) {

        final List<JobRecord> jobRecords = new ArrayList<>();

        final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            final XMLStreamReader streamReader = inputFactory.createXMLStreamReader(is);
            boolean insideSentinel = false;
            JobRecord jobRecord = null;
            while (streamReader.hasNext()) {
                if (streamReader.isStartElement()) {
                    if (configuration.getSentinelNodeElementName().equals(streamReader.getLocalName())) {
                        insideSentinel = true;
                        jobRecord = new JobRecord();
                    }
//                    System.out.printf("startElement = localName: [%s] inside:%s\n", streamReader.getLocalName(), insideSentinel);

                    if (insideSentinel) {
                        mapper.mapXmlPropertiesToJobRecord(configuration, streamReader, jobRecord);
                    }
                }

                if (streamReader.isEndElement()) {
                    if (configuration.getSentinelNodeElementName().equals(streamReader.getLocalName())) {
                        insideSentinel = false;
                        jobRecords.add(jobRecord);
                    }
//                    System.out.printf("endElement = localName: [%s] inside:%s\n", streamReader.getLocalName(), insideSentinel);
                }

                streamReader.next();        /* !!! */
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException("unable to parse XML using stream reader", e);
        }

        return jobRecords;
    }

    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }


}

