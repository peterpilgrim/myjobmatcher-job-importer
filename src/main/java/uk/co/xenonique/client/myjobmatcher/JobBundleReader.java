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

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The type JobBundleReader
 *
 * @author Peter Pilgrim (peter)
 */
public class JobBundleReader {

    private static CharsetEncoder isoEncoder = Charset.forName("ISO-8859-1").newEncoder();
    private static CharsetDecoder utf8Decoder = Charset.forName("UTF-8").newDecoder();

    static {
        // Instruct the US-ASCII encoder to ignore malformed and unmappable characters
        isoEncoder.onMalformedInput(CodingErrorAction.IGNORE);
        isoEncoder.onUnmappableCharacter(CodingErrorAction.IGNORE);

        // Extra bit: instruct our UTF-8 decoder to map malformed and unmapped characters to '?'
        utf8Decoder.onMalformedInput(CodingErrorAction.REPLACE);
        utf8Decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
        utf8Decoder.replaceWith("?");
    }

    private static String notNullAndNormalise(String x) {
        if (x == null)
            return "";
        else {
            // See also: http://stackoverflow.com/questions/240546/remove-html-tags-from-a-string
            x = x.trim().replaceAll("\\<[^>]*>", "");

            // Remove UTF-8 characters
            try {
                CharBuffer charBuffer = CharBuffer.wrap(x);
                ByteBuffer encodeBuffer = isoEncoder.encode(charBuffer);
                CharBuffer decodeBuffer = utf8Decoder.decode(encodeBuffer);
                return decodeBuffer.toString();
            } catch (CharacterCodingException e) {
                throw new RuntimeException("failure to apply encoding", e);
            }
        }
    }

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
                        final String localName = streamReader.getLocalName();
                        if (configuration.getTitleElementName().equalsIgnoreCase(localName)) {
                            jobRecord.setTitle(notNullAndNormalise(streamReader.getElementText()));
                        }
                        if (configuration.getDescriptionElementName().equalsIgnoreCase(localName)) {
                            jobRecord.setDescription(notNullAndNormalise(streamReader.getElementText()));
                        }
                        if (configuration.getLocationElementName().equalsIgnoreCase(localName)) {
                            jobRecord.setLocation(notNullAndNormalise(streamReader.getElementText()));
                        }
                        if (configuration.getReferenceElementName().equalsIgnoreCase(localName)) {
                            jobRecord.setReference(notNullAndNormalise(streamReader.getElementText()));
                        }
                        if (configuration.getUrlElementName().equalsIgnoreCase(localName)) {
                            jobRecord.setUrl(notNullAndNormalise(streamReader.getElementText()));
                        }
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
}

