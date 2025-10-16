package com.example.demo.services.auth;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

	/*private Logger logger = LogManager.getLogger(getClass());

    // Claim constants
    private static final String ROLES_CLAIM = "roles";
    private static final String SESSION_TOKEN_CLAIM = "sessionToken";
    public static final String SCOPES_CLAIM = "scopes";

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms}")
    private long jwtExpirationMs;

    @Value("${app.jwt.refresh-expiration-ms}")
    private long refreshExpirationMs;

    @Value("${app.jwt.issuer}")
    private String jwtIssuer;

    // Generate token with username, roles, scopes and session token
    public String generateToken(String username,
                                Collection<? extends GrantedAuthority> authorities,
                                Set<String> scopes,
                                String sessionToken) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SESSION_TOKEN_CLAIM, sessionToken);
        claims.put(ROLES_CLAIM, authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        claims.put(SCOPES_CLAIM, String.join(",", scopes));
        return buildToken(claims, username, jwtExpirationMs);
    }

    // Generate token from username/email
    public String generateToken(String username) {
        return buildToken(new HashMap<>(), username, jwtExpirationMs);
    }

    // Generate refresh token from username/email
    public String generateRefreshToken(String username) {
        return buildToken(new HashMap<>(), username, refreshExpirationMs);
    }

    public String generateToken(Map<String, Object> extraClaims, String username) {
        return buildToken(extraClaims, username, jwtExpirationMs);
    }

    public String generateToken(String username, String sessionToken) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SESSION_TOKEN_CLAIM, sessionToken);
        return buildToken(claims, username, jwtExpirationMs);
    }

    private String buildToken(Map<String, Object> claims,
                              String subject,
                              long expiration) {
        return Jwts.builder()
                .setIssuer(jwtIssuer)
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // Get expiration timestamp from token
    public long getExpirationMillisFromToken(String token) {
        return extractClaim(token, Claims::getExpiration).getTime();
    }

    // Validate token against UserDetails
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        boolean validUser = username.equals(userDetails.getUsername());
        boolean validRoles = validateRoles(token, userDetails.getAuthorities());
        return validUser && validRoles && !isTokenExpired(token);
    }

    private boolean validateRoles(String token,
                                  Collection<? extends GrantedAuthority> authorities) {
        List<String> tokenRoles = extractRoles(token);
        Set<String> userRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        return userRoles.containsAll(tokenRoles);
    }

    // Extract username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract roles from token
    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> claims.get(ROLES_CLAIM, List.class));
    }


    public String extractScopes(String token){
        return extractClaim(token, claims -> claims.get(SCOPES_CLAIM, String.class));
    }

    // Extract session token from JWT
    public String extractSessionToken(String token) {
        return extractClaim(token, claims -> claims.get(SESSION_TOKEN_CLAIM, String.class));
    }

    // Generic claim extraction
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Unified validation method
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }*/

}
