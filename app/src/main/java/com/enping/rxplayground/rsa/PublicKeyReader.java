package com.enping.rxplayground.rsa;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class PublicKeyReader {
    public static PublicKey get(InputStream instream)
            throws Exception {

        byte[] encodedKey = new byte[instream.available()];
        instream.read(encodedKey);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(publicKeySpec);
    }
}
