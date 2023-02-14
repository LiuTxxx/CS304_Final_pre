package org.modelmapper.internal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import java.util.UUID;
import lombok.Data;
//CS304 (manually written) Issue link: https://github.com/modelmapper/modelmapper/issues/164
public class CyclicMappingTest {
    ModelMapper modelMapper = new ModelMapper();
    @Data
    private static class Action {
        UUID id;
        Action previous;
    }

    @Data
    private static class ActionDTO {
        UUID id;
        UUID previousId;
    }

    @Test
    public void testPreviousLink() {

        ActionDTO dto = new ActionDTO();
        dto.setPreviousId(UUID.randomUUID());
        dto.setId(UUID.randomUUID());

        Action model = modelMapper.map(dto, Action.class);
        Assertions.assertInstanceOf(Action.class, model);
        Assertions.assertEquals(model.getId(), dto.getId());
        Assertions.assertNotNull(model.getPrevious()); // fails here when strict
        Assertions.assertInstanceOf(Action.class, model.getPrevious());
        Assertions.assertEquals(model.getPrevious().getId(), dto.getPreviousId()); // fails here with normal matching
    }

    @Test
    public void testPreviousLink1() {

        ActionDTO dto = new ActionDTO();
        dto.setPreviousId(UUID.randomUUID());
        Action model = modelMapper.map(dto, Action.class);
        Assertions.assertInstanceOf(Action.class, model);
        Assertions.assertEquals(model.getId(), dto.getId());
        Assertions.assertNull(dto.getId());
        Assertions.assertEquals(model.getPrevious().getId(), dto.getPreviousId()); // fails here with normal matching
    }

}
