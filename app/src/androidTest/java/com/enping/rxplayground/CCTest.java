package com.enping.rxplayground;
import android.support.test.runner.AndroidJUnit4;
import android.util.Base64;
import android.util.Log;

import com.enping.rxplayground.rsa.RSACipher;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CCTest {

    @Test
    public void testGeneratePublicKeyFromDerFile(){
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("cert.der");
            PublicKey pub = RSACipher.stringToPublicKey(stream);
            String based64 = Base64.encodeToString(pub.getEncoded(),Base64.DEFAULT);
            String based64WithTag = "-----BEGIN PUBLIC KEY-----\n" + based64 + "-----END PUBLIC KEY-----";
            Log.d("RSA",based64WithTag);
            Log.d("RSA",PUBLIC_KEY);
            assertTrue(based64WithTag.replace("\n","").equals(PUBLIC_KEY.replace("\n","")));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGeneratePublicKeyFromPemFile(){
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("cert.pem");
            PublicKey pub = RSACipher.stringToPublicKey(stream);
            String based64 = Base64.encodeToString(pub.getEncoded(),Base64.DEFAULT);
            String based64WithTag = "-----BEGIN PUBLIC KEY-----\n" + based64 + "-----END PUBLIC KEY-----";
            Log.d("RSA",based64WithTag);
            Log.d("RSA",PUBLIC_KEY);
            assertTrue(based64WithTag.replace("\n","").equals(PUBLIC_KEY.replace("\n","")));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }

    public static final String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAq0WEh0Xt0sF+B138CeD2\n" +
            "2b0EqMyqcBRah4o3XthJj1h4jytj6HDIsx+NLExFNzMIeq1CVVST0MznPgykA7Ci\n" +
            "ffxWkphfNGHfyBo7NNA3SwmNoHt+Nn3hgAnSI35BvNGxxtKf2kv5T5AXbdgFl09i\n" +
            "akdtKU/IiVE1kVGU08dN8OIHtCtomDGq5hMpsnGf71UWXGQjMzDzZJHxLkYPdCjC\n" +
            "FuElV4iKGobmhxtVoLJ+NjIatiRXTEOX6ZVxeMdgB++gQc4skwMZ4c6LIW6ZlJAW\n" +
            "U0+fm1h6Vo/DvuiOal29BErQcVyKEZ8inE/no3ee2674KGHX9ivctz2o34sNw6Id\n" +
            "5TFyt259sDm05XwvHVoOgX3n/T/ZPlny7li9WCFIwQcZdqRhkeIZpWsENDW0Wx7s\n" +
            "tBCWn7PFgSw6kJ405LGYBe7NcYdDNNUpsaBsEVVU549xAjksICsgKat8pMGaTXGU\n" +
            "/zVLA3WCcOnLGUK654257OKWaj8hJPrz9e9o+quj4fprAtIEAdJx/beD9xbOQUeg\n" +
            "MTFBEQVCj/vp+zmXzp5Qu9AW3vfmB0m/Kt3ViDCTyDT5B7X7odI5+9iI8uaYcNvx\n" +
            "XB/IwJPHZI+IzZbkAyqMkEJghjT/i5xxFH8V8jDtOi6J09p7dWukoEOfYTlsUf/h\n" +
            "403xhZGkMxN5X25HXsYwkD0CAwEAAQ==\n" +
            "-----END PUBLIC KEY-----";

}