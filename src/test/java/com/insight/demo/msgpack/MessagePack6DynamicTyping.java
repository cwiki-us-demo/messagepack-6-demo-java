package com.insight.demo.msgpack;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.msgpack.template.Templates.TString;
import static org.msgpack.template.Templates.tList;

/**
 * MessagePack6Objects
 *
 * @author yhu
 */
public class MessagePack6DynamicTyping {
    final Logger logger = LoggerFactory.getLogger(MessagePack6DynamicTyping.class);


    /**
     * Test MessagePack6Objects
     */
    @Test
    public void MessagePack6DynamicTyping() {
        logger.debug("MessagePack6Objects for Objects");

        // Create serialize objects.
        List<String> src = new ArrayList<String>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");

        MessagePack msgpack = new MessagePack();

        try {

            // Serialize
            byte[] raw = msgpack.write(src);

            // Deserialize directly using a template
            List<String> dst1 = msgpack.read(raw, tList(TString));

            // Or, Deserialze to Value then convert type.
            Value dynamic = msgpack.read(raw);
            List<String> dst2 = new Converter(dynamic).read(tList(TString));

        } catch (Exception ex) {
            logger.error("MessagePack Serialization And Deserialization error", ex);
        }
    }
}
