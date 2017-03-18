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

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

/**
 * The type NormalizerDefault
 *
 * @author Peter Pilgrim (peter)
 */
public class NormalizerDefault implements Normalizer {

    private final CharsetEncoder isoEncoder = Charset.forName("ISO-8859-1").newEncoder();
    private final CharsetDecoder utf8Decoder = Charset.forName("UTF-8").newDecoder();

    public NormalizerDefault() {
        // Instruct the US-ASCII encoder to ignore malformed and unmappable characters
        isoEncoder.onMalformedInput(CodingErrorAction.IGNORE);
        isoEncoder.onUnmappableCharacter(CodingErrorAction.IGNORE);

        // Extra bit: instruct our UTF-8 decoder to map malformed and unmapped characters to '?'
        utf8Decoder.onMalformedInput(CodingErrorAction.REPLACE);
        utf8Decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
        utf8Decoder.replaceWith("?");
    }


    public String notNullAndNormalise(String x) {
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

}
