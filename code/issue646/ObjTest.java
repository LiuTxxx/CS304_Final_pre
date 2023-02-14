import org.junit.Test;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ObjTest {
    @Test
    public void testAllNull() {
        ModelMapper mapper = new ModelMapper();
        Obj obj1 = new Obj("1","2","3","4","5");
        Obj obj2 = new Obj(null,null,null,null,null);
        mapper.getConfiguration().setPropertyCondition(Conditions.isNull());
        mapper.map(obj2,obj1);
        assertEquals("a:null b:null c:null d:null e:null", obj1.toString());
    }

    @Test
    public void testOnlyTwoNull() {
        ModelMapper mapper = new ModelMapper();
        Obj obj1 = new Obj("1","2","3","4","5");
        Obj obj2 = new Obj("10","20",null,null,null);
        mapper.getConfiguration().setPropertyCondition(Conditions.isNull());
        mapper.map(obj2,obj1);
        assertEquals("a:1 b:2 c:null d:null e:null", obj1.toString());
    }

    @Test
    public void testAllNotNull() {
        ModelMapper mapper = new ModelMapper();
        Obj obj1 = new Obj("1","2","3","4","5");
        Obj obj2 = new Obj("10","20","30","40","50");
        mapper.getConfiguration().setPropertyCondition(Conditions.isNull());
        mapper.map(obj2,obj1);
        assertEquals("a:1 b:2 c:3 d:4 e:5", obj1.toString());
    }
}
