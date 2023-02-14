package org.modelmapper.internal.converter;

import java.util.UUID;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

// CS304 Issue link:  https://github.com/modelmapper/modelmapper/issues/660
class UuidConverter implements ConditionalConverter<Object, UUID> {
    /**
     * Converts the MappingContext.getSource() to an instance of UUID.
     * @param context of current mapping process
     * @throws NullPointerException if context is null.
     *  already defined in the TypeMapStore
     */
    public UUID convert(MappingContext<Object, UUID> context) {
      Object source = context.getSource();
      if (source == null) {
          return null;
      }
      Class<?> sourceType = context.getSourceType();

      String string = sourceType.isArray() && sourceType.getComponentType() == Character.TYPE
              || sourceType.getComponentType() == Character.class
              ? String.valueOf((char[]) source) : source.toString();
        return UUID.fromString(string);
    }

    /**
     *Determines whether the converter matches and supports conversion from sourceType to destinationType.
     *
     * @param sourceType source type to match.
     * @param destinationType destination type to match.
     * @return ConditionalConverter.MatchResult.FULL if sourceType and destinationType are matched
     * ConditionalConverter.MatchResult.PARTIAL if destinationType is matched
     * ConditionalConverter.MatchResult.NONE if destinationType is not matched
     * @throws IllegalArgumentException is {@code sourceType} or {@code destinationType} are null.
     */
    public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
        return destinationType == UUID.class ? sourceType == UUID.class ? MatchResult.FULL
                : MatchResult.PARTIAL : MatchResult.NONE;
    }
}