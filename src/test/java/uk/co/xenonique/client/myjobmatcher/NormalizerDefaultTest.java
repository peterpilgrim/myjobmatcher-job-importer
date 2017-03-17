package uk.co.xenonique.client.myjobmatcher;

import org.junit.Before;
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

    @Test
    public void normalize_test_with_utf8_characters() {
        assertThat(normalizer.notNullAndNormalise(UTF8_TEXT), is(UTF8_TEXT));
    }

    @Test
    public void normalize_test_with_non_utf8_characters() {
        assertThat(normalizer.notNullAndNormalise(NON_UTF8_TEXT_INPUT), is(NON_UTF8_TEXT_OUTPUT));
    }
}
