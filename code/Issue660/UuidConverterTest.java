package org.modelmapper.internal.converter;

import org.modelmapper.spi.ConditionalConverter;
import org.testng.annotations.Test;
import java.util.UUID;

import static org.testng.Assert.assertEquals;

// CS304 (manually written) Issue link:  https://github.com/modelmapper/modelmapper/issues/660
public class UuidConverterTest extends AbstractConverterTest {
    public UuidConverterTest() {
        super(new UuidConverter(), UUID.class);
    }
    /**
     * Test MatchResult.FULL if sourceType and destinationType are matched.
     */
    @Test
    public void testFull() {
        assertEquals(converter.match(UUID.class, UUID.class), ConditionalConverter.MatchResult.FULL);
    }
    /**
     * Test MatchResult.PARTIAL if destinationType is matched.
     */
    @Test
    public void testPartial(){
        assertEquals(converter.match(String.class, UUID.class), ConditionalConverter.MatchResult.PARTIAL);
    }
    /**
     * Test MatchResult.NONE if destinationType is not matched.
     */
    @Test
    public void testNone(){
        assertEquals(converter.match(String.class, String.class), ConditionalConverter.MatchResult.NONE);
    }
    /**
     * Test whether the convert result's type is true.
     */
    @Test
    public void testConversionType(){
        assertEquals(UUID.class,convert("87180668-ace8-4204-950e-dda74c55f703").getClass());
    }
    /**
     * Test whether the convert result's value is true.
     */
    @Test
    public void testConversionValues() {
        assertEquals("87180668-ace8-4204-950e-dda74c55f703", convert("87180668-ace8-4204-950e-dda74c55f703").toString());
    }

}
