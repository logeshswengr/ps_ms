package com.project.userservice.logging;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.project.userservice.annotations.Mask;
import com.project.userservice.annotations.PII;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonConverter {

    private static final Logger log = LoggerFactory.getLogger(JsonConverter.class);

    public static String convertToJsonString(Object object) {
        if (object == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new BeanSerializerModifier() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription desc,
                                                              JsonSerializer<?> serializer) {

                        if (doesSuperClassAnnotated(desc) || isSecurityAnnotated(desc)) {
                            return new MaskSerializer((JsonSerializer<Object>) serializer);

                        }else {
                            return serializer;
                        }
                    }

                    private boolean doesSuperClassAnnotated(BeanDescription desc) {
                        Class<?> objectClass = desc.getBeanClass();

                        // Do not check on Java Primitive type as it does not have super class.
                        while(!objectClass.getName().equalsIgnoreCase("java.lang.Object")
                                && !objectClass.isPrimitive()) {

                            if (objectClass.isAnnotationPresent(Mask.class)) {
                                return true;
                            } else {
                                objectClass = objectClass.getSuperclass();
                            }

                        }
                        return false;
                    }

                    private boolean isSecurityAnnotated(BeanDescription desc) {
                        return desc.getBeanClass().isAnnotationPresent(PII.class);
                    }
                });

            }
        });

        String jsonString = null;

        try {
            if (String.class.isAssignableFrom(object.getClass())) {
                jsonString = (String) object;
            } else {
                jsonString = mapper.writeValueAsString(object);
            }
        } catch (JsonGenerationException e) {
            log.error("JsonGenerationException:  " + e.getMessage());
        } catch (JsonMappingException e) {
            log.error("JsonMappingException:  " + e.getMessage());
        } catch (IOException e) {
            log.error("IOException: " + e.getMessage());
        }
        return jsonString;
    }
}
