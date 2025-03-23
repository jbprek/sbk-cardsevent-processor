package com.foo.cardevent.configuration.serialization;

import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class AvroSerializer<T extends SpecificRecordBase> implements Serializer<T> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null) {
            return null;
        }

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            DatumWriter<T> datumWriter = new SpecificDatumWriter<>(
                    (Class<T>) data.getClass());

            var encoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
            datumWriter.write(data, encoder);
            encoder.flush();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new SerializationException("Error serializing Avro message", e);
        }
    }

    @Override
    public void close() {
    }
}