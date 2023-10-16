package com.project.userservice.logging;

public interface SensitiveMask {
    String mask(String value);

    String mask(String value, int keepLastDigits);

}
