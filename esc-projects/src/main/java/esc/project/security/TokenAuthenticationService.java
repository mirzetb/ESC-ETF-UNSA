package esc.project.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security
            .authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;





class TokenAuthenticationService {
  static final long EXPIRATIONTIME = 864_000_000; 
  static final String SECRET = "ThisIsASecret";
  static final String TOKEN_PREFIX = "Bearer";
  static final String HEADER_STRING = "Authorization";

  static void addAuthentication(HttpServletResponse res, String username, Object[] grantedAuthorities) {
	
	
    String JWT = Jwts.builder()
        .setSubject(username) 
        
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .claim("UserRole", grantedAuthorities[0].toString())       	
        .compact();
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    Cookie c = new Cookie("Auth", JWT);
    c.setHttpOnly(true);
    res.addCookie(c);
  }

  static Authentication getAuthentication(HttpServletRequest request) {
    //String token = request.getHeader(HEADER_STRING);
    Cookie[] cookies = request.getCookies();
    String token = "";
    if(cookies != null)
    {
    	for(Cookie c : cookies)
    	{
    		if(c.getName().equals("Auth")){
    			token = c.getValue();
    		}
    	}
    }
    
    if (token != null && token != "") {
      // parse the token.
      String user = Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
          .getBody()         
          .getSubject();
      
      Claims claims = Jwts.parser()
    		  .setSigningKey(SECRET)
    		  .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
    		  .getBody();
      
      List<GrantedAuthority> authorities = new ArrayList<>();
      String value = claims.get("UserRole").toString();      
      authorities.add(new SimpleGrantedAuthority(claims.get("UserRole").toString()));      

      return user != null ?
          new UsernamePasswordAuthenticationToken(user, null, authorities) :    	  
          null;
    }
    return null;
  }
}