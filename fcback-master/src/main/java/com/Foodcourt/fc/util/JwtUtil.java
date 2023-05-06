package com.Foodcourt.fc.util;

import com.Foodcourt.fc.dto.UserDetailsDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtUtil {
    private String SECRET_KEY = "Rugged_Boy_Veda";
    private Long jwtExpirationInMillis;
    public long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60;

    public List<String> blackedJWT = new ArrayList<>();
    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    public void logout(HttpServletRequest request)
    {
        final String authorizationHeader = (request).getHeader("Authorization");
        String jwt  = authorizationHeader.substring(7);
        blackedJWT.add(jwt);
        return;
    }

    public boolean legitJWT(String jwt)
    {
        return blackedJWT.contains(jwt);
    }

    public Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userDetails)
    {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    private String createToken(Map<String, Object> claims, String subject)
    {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails)
    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }

    public int getUserIdFromSecurityContext()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsDTO users = (UserDetailsDTO) authentication.getPrincipal();
        return users.getId();
    }

    public void cleanExpired() {
        for(String jwt:blackedJWT)
        {
            if(isTokenExpired(jwt))
            {
                blackedJWT.remove(jwt);
                if(blackedJWT.size()==0)
                {
                    return;
                }
            }
        }
    }
}
