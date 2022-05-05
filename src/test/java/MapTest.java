import castler.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


public class MapTest {
    Map m;
    int size;

    @BeforeEach
    void setUp(){
        size = 15;
        m = new Map(size,1);
    }

    @Test
    void generatedMapIsValid(){
        assertTrue(m.isValidPath(new Point(0, size - 1), new Point(size - 1, 0)));
    }

    @Test
    void setSelectTest(){
        m.setSelect(150,150);
        assertTrue(m.getTile(150,150).getSelected());
    }

    @Test
    void inMap(){
        assertFalse(m.inMap(new Point(16,0)));
        assertFalse(m.inMap(new Point(0,16)));
        assertFalse(m.inMap(new Point(-1,-1)));
    }


}
