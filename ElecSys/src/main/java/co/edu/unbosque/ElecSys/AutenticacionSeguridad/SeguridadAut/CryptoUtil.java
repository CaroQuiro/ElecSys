package co.edu.unbosque.ElecSys.AutenticacionSeguridad.SeguridadAut;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {

    private static final String ALGORITHM = "AES";

    // 32 caracteres = 256 bits
    private static final String SECRET_KEY = "vc_electricos_clave_256_bits!!";

    private static SecretKeySpec getKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
    }

    /* =========================
       ENCRIPTAR
       ========================= */
    public static String encriptar(String texto) {
        try {
            if (texto == null) return null;

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getKey());

            byte[] encrypted = cipher.doFinal(texto.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar", e);
        }
    }

    /* =========================
       DESENCRIPTAR
       ========================= */
    public static String desencriptar(String textoEncriptado) {
        try {
            if (textoEncriptado == null) return null;

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getKey());

            byte[] decoded = Base64.getDecoder().decode(textoEncriptado);
            byte[] decrypted = cipher.doFinal(decoded);

            return new String(decrypted);

        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar", e);
        }
    }
}
