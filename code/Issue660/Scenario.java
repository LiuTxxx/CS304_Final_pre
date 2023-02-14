package org.modelmapper.bugs;
import org.modelmapper.ModelMapper;
import org.testng.annotations.Test;
import java.util.UUID;

public class Scenario {
    // CS304 (manually written) Issue link:  https://github.com/modelmapper/modelmapper/issues/660
    /**
     * Try to project the type string to UUID, and should project to the UUID type successfully
     */
    @Test
    public void testSimple () {
        String a = "87180668-ace8-4204-950e-dda74c55f703";
        ModelMapper modelMapper = new ModelMapper();
        UUID uuid = modelMapper.map(a,UUID.class);
        assert uuid.toString().equals(a);
    }
    // CS304 (manually written) Issue link:  https://github.com/modelmapper/modelmapper/issues/660
    /**
     * UUIDObj and UUIDObjDTO are two objects, and it can flatten and project from each other.
     * The fields in these two java classes should match correctly.
     */
    @Test
    public void test2Complex() {
        ModelMapper modelMapper = new ModelMapper();
        UUIDObj uuidObj = new UUIDObj();
        UUIDObjDTO uuidObjDTO= modelMapper.map(uuidObj,UUIDObjDTO.class);
        assert uuidObjDTO.getUuid().toString().equals(uuidObj.getUuid());
    }
}
