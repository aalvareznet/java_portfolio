package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.jwt;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.ClienteInfoPersonaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.ClienteMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ClienteRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.User;
    
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private ClienteMapper clienteMapper;

    public String getToken(User user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(HashMap<String,Object> extraClaims, User user) {
        Optional<Cliente> clienteOptional = clienteRepositorio.findById(user.getId());
        ClienteInfoPersonaDto cliente = clienteMapper.ConvertEntityToInfoDto(clienteOptional.get());
        return Jwts.builder()
                .claims(extraClaims)
                .claim("userId", user.getId())
                .claim("autorities", "ROLE_"+user.getRole().name())
                .claim("nombre", cliente.getNombrePersona())
                .claim("apellido", cliente.getPrimerApellidoPersona() + " " + cliente.getSegundoApellidoPerson())
                .claim("correo", cliente.getEmail())
                .claim("telefono", cliente.getTelefono())
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private Claims getAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpirationDate(String token){
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpirationDate(token).before(new Date());
    }
}
