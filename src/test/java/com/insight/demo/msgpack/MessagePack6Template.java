package com.insight.demo.msgpack;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.template.Template;
import org.msgpack.unpacker.Unpacker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.msgpack.template.Templates.*;

/**
 * MessagePack6Template
 *
 * @author yhu
 */
public class MessagePack6Template {
    final Logger logger = LoggerFactory.getLogger(MessagePack6Template.class);


    /**
     * Test MessagePack6Template
     */
    @Test
    public void testMessagePack6Template() {
        logger.debug("MessagePack6Template for Template");

        MessagePack msgpack = new MessagePack();
        try {

            // Create templates for serializing/deserializing List and Map objects
            Template<List<String>> listTmpl = tList(TString);
            Template<Map<String, String>> mapTmpl = tMap(TString, TString);

            //
            // Serialization
            //

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Packer packer = msgpack.createPacker(out);

            // Serialize List object
            List<String> list = new ArrayList<String>();
            list.add("msgpack");
            list.add("for");
            list.add("java");
            packer.write(list); // List object

            // Serialize Map object
            Map<String, String> map = new HashMap<String, String>();
            map.put("sadayuki", "furuhashi");
            map.put("muga", "nishizawa");
            packer.write(map); // Map object

            //
            // Deserialization
            //

            byte[] bytes = out.toByteArray();
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            Unpacker unpacker = msgpack.createUnpacker(in);

            // to List object
            List<String> dstList = unpacker.read(listTmpl);

            // to Map object
            Map<String, String> dstMap = unpacker.read(mapTmpl);

        } catch (Exception ex) {
            logger.error("MessagePack Serialization And Deserialization error", ex);
        }
    }
}
