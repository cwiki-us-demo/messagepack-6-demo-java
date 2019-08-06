package com.insight.demo.msgpack;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * MessagePack6Objects
 *
 * @author yhu
 */
public class MessagePack6Object {
    final Logger logger = LoggerFactory.getLogger(MessagePack6Object.class);

    /**
     * MessageData Message Object
     */
    @Message // Annotation
    public static class MessageData {
        // public fields are serialized.
        public String uuid;
        public String name;
        public double version;
    }


    /**
     * Test MessagePack6Objects
     */
    @Test
    public void testMessagePack6Objects() {
        logger.debug("MessagePack6Objects for Objects");

        String uuid = UUID.randomUUID().toString();

        // INIT OBJ
        MessageData src = new MessageData();
        src.uuid = uuid;
        src.name = "MessagePack6";
        src.version = 0.6;

        try {
            MessagePack msgPack = new MessagePack();

            // Serialization
            logger.debug("------ Serialization ------");
            byte[] bytes = msgPack.write(src);
            logger.debug("Bytes Array Length: [{}]", bytes.length);

            // Deserialization
            logger.debug("------ Deserialization ------");
            MessageData dst = msgPack.read(bytes, MessageData.class);
            logger.debug("Check Object for UUID: [{}]", dst.uuid);

            assertEquals(uuid, dst.uuid);

        } catch (Exception ex) {
            logger.error("MessagePack Serialization And Deserialization error", ex);
        }
    }
}
