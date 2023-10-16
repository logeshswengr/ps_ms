package com.project.userservice.logging;

public class PIIMask implements SensitiveMask {

    @Override
    public String mask(String value) {
        return null;
    }

    @Override
    public String mask(String piiInput, int keepLastDigits) {
        if (piiInput == null) {
            return null;
        }

        String piiString = "";
        if (piiInput.length() - keepLastDigits >= 0) {
            String piiInputForMask = piiInput.substring(0, (piiInput.length() - keepLastDigits))+"PII";
            String piiInputNotForMask = piiInput.substring((piiInput.length() - keepLastDigits));
            String piiHashResult = Integer.toHexString(piiInputForMask.hashCode());
            piiString = piiHashResult + piiInputNotForMask;
        }
        return piiString;
    }
}
