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

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * A unit test NormalizerDefaultTest to verify the operation of {@link NormalizerDefault}
 *
 * @author Peter Pilgrim
 */
public class NormalizerDefaultTest {

    public static final String UTF8_TEXT = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String NON_UTF8_TEXT_INPUT = "LONDON 〠.〠";
    public static final String NON_UTF8_TEXT_OUTPUT = "LONDON .";
    private Normalizer normalizer;

    @Before
    public void setup() {
        normalizer = new NormalizerDefault();
    }

    /**
     * Tests break with Gradle. However works inside the IDE. Maybe the Gradle deamon
     */
    @Test
    @Ignore
    public void normalize_test_with_non_utf8_characters() {
        assertThat(normalizer.notNullAndNormalise(NON_UTF8_TEXT_INPUT), is(NON_UTF8_TEXT_OUTPUT));
    }

    @Test
    public void normalize_test_with_utf8_characters() {
        assertThat(normalizer.notNullAndNormalise(UTF8_TEXT), is(UTF8_TEXT));
    }

}
