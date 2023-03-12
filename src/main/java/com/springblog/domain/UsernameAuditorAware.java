package com.springblog.domain;

import com.springblog.config.auth.PrincipalDetails;
import com.springblog.domain.type.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class UsernameAuditorAware implements AuditorAware<String> {
  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    log.info("authentication : {}", authentication);

//    authentication : AnonymousAuthenticationToken [Principal=anonymousUser, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=6375E7BB23D79CF2E29AD003C986F49F], Granted Authorities=[ROLE_ANONYMOUS]]
//    authentication : UsernamePasswordAuthenticationToken [Principal=com.springblog.config.auth.PrincipalDetails@1601e221, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=6375E7BB23D79CF2E29AD003C986F49F], Granted Authorities=[com.springblog.config.auth.PrincipalDetails$$Lambda$1822/0x0000000801906968@6244fbf3]]

    if (authentication instanceof UsernamePasswordAuthenticationToken) {
      PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
      return Optional.of(principal.getUsername());
    }

    return Optional.of(RoleType.ADMIN.name());
  }

}
