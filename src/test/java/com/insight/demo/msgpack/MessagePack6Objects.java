package com.insight.demo.msgpack;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;
import org.msgpack.packer.Packer;
import org.msgpack.unpacker.Unpacker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * MessagePack6Objects
 *
 * @author yhu
 */
public class MessagePack6Objects {
    final Logger logger = LoggerFactory.getLogger(MessagePack6Objects.class);

    /**
     * MessageData Message Objects
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
        MessageData src1 = new MessageData();
        src1.uuid = uuid;
        src1.name = "MessagePack6-src1";
        src1.version = 0.6;

        MessageData src2 = new MessageData();
        src2.uuid = uuid;
        src2.name = "MessagePack6-src2";
        src2.version = 10.6;

        MessageData src3 = new MessageData();
        src3.uuid = uuid;
        src3.name = "MessagePack6-src3";
        src3.version = 1.6;

        try {
            MessagePack msgPack = new MessagePack();

            // Serialization
            logger.debug("------ Serialization ------");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Packer packer = msgPack.createPacker(out);
            packer.write(src1);
            packer.write(src2);
            packer.write(src3);

            byte[] bytes = out.toByteArray();
            logger.debug("Bytes Array Length: [{}]", bytes.length);

            // Deserialization
            logger.debug("------ Deserialization ------");
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            Unpacker unpacker = msgPack.createUnpacker(in);

            MessageData dst1 = unpacker.read(MessageData.class);
            MessageData dst2 = unpacker.read(MessageData.class);
            MessageData dst3 = unpacker.read(MessageData.class);

            logger.debug("Check Object for UUID: [{}]", dst1.uuid);

            assertEquals(uuid, dst1.uuid);

        } catch (Exception ex) {
            logger.error("MessagePack Serialization And Deserialization error", ex);
        }
    }
}
