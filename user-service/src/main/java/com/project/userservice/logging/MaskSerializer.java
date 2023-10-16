package com.project.userservice.logging;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project.userservice.annotations.Mask;
import com.project.userservice.annotations.PII;

import java.io.IOException;
import java.lang.reflect.Field;

public class MaskSerializer extends JsonSerializer<Object> {

    private JsonSerializer<Object> defaultSerializer;

    public MaskSerializer(JsonSerializer<Object> serializer) {
        defaultSerializer = serializer;
    }


    @Override
    public void serialize(Object obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {


        Class<?> objectClass = obj.getClass();

        jgen.writeStartObject();
        while (objectClass != null) {

            if (objectClass.getName().equalsIgnoreCase("java.lang.Object")) {
                break;
            }

            if (objectClass.isAnnotationPresent(Mask.class)) {
                for (Field field : objectClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        if (field.isAnnotationPresent(PII.class)) {
                            int keepLastDigits = 0;
                            String maskedValue = null;
                            if (field.isAnnotationPresent(PII.class)) {
                                keepLastDigits = field.getAnnotation(PII.class).keepLastDigits();
                                SensitiveMask sensitiveData = new PIIMask();
                                if (sensitiveData != null) {
                                    maskedValue = sensitiveData.mask((String) field.get(obj), keepLastDigits);
                                }
                            }
                            jgen.writeObjectField(field.getName(), maskedValue);
                        } else {
                            jgen.writeObjectField(field.getName(), field.get(obj));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            objectClass = objectClass.getSuperclass();
        }
        jgen.writeEndObject();
    }

}