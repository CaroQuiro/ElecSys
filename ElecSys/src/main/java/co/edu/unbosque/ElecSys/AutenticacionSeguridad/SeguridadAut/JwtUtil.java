package co.edu.unbosque.ElecSys.AutenticacionSeguridad.SeguridadAut;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "clave_super_secreta";

    public static String generarToken(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 86400000) // 24h
                )
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static String extraerCorreo(String token) {
        return obtenerClaims(token).getSubject();
    }

    public static boolean esTokenValido(String token) {
        try {
            obtenerClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Claims obtenerClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}

