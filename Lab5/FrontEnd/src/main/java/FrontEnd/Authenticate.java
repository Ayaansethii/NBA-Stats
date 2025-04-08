/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import Helper.Manager;
import Persistence.ManagerDAO;

public class Authenticate {
    private final Key signingKey;
    private final long ttlMillis = 3600000; // 1 hour validity
 
    public Authenticate() {
        // Create a signing key; ensure the secret is long enough (here using a sample base64 string)
        byte[] keyBytes = DatatypeConverter.parseBase64Binary("my-secret-key-my-secret-key");
        signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
    
    // Authenticate using the DAO and create a JWT containing managerId and username if valid
    public String authenticate(String username, String password) {
        ManagerDAO dao = new ManagerDAO();
        Manager manager = dao.getManagerByUsername(username);
        if(manager != null && manager.getPassword().equals(password)) {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            JwtBuilder builder = Jwts.builder()
                    .setSubject(username)
                    .claim("managerId", manager.getId())
                    .setIssuedAt(now)
                    .setExpiration(new Date(nowMillis + ttlMillis))
                    .signWith(signingKey, SignatureAlgorithm.HS256);
            return builder.compact();
        }
        return null;
    }
    
    // Validate token if needed later
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}