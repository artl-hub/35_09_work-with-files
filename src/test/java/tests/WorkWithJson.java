package tests;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import com.fasterxml.jackson.databind.ObjectMapper;
import tests.model.Widget;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

    public class WorkWithJson {

        @Test
        void jsonParsingTest() throws Exception {
            ObjectMapper mapper = new ObjectMapper();

            try (InputStream is = getClass().getClassLoader().getResourceAsStream("widget.json")) {

                Widget widget = mapper.readValue(is, Widget.class);

                assertEquals("on", widget.getDebug());
                assertNotNull(widget.getImage());

                assertEquals("Images/Sun.png", widget.getImage().getSrc());
                assertEquals("sun1", widget.getImage().getName());
                assertEquals(250, widget.getImage().gethOffset());
                assertEquals(250, widget.getImage().getvOffset());
                assertEquals("center", widget.getImage().getAlignment());
            }
        }
    }



