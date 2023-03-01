package br.com.events.band.application.config.filters;

import br.com.events.band.application.config.filters.exception.NoTokenReceivedException;
import br.com.events.band.domain.io.feign.msAuth.person.getAuthenticatedPerson.out.GetAuthenticatedPersonInformationResult;
import br.com.events.band.domain.mapper.auth.AuthenticatedPersonMapper;
import br.com.events.band.infrastructure.exception.BusinessException;
import br.com.events.band.infrastructure.feign.msAuth.PersonMsAuthFeignClient;
import br.com.events.band.util.FilterExceptionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * This class intercept every request and check if there is a Authorization header containing a JWT and validate it
 *
 * @author Gabriel Guimarães de Almeida
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final PersonMsAuthFeignClient personMsAuthFeignClient;
    private final FilterExceptionUtil filterExceptionUtil;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
        log.info("Filtering by jwt token");

        var token = request.getHeader("Authorization");

        if (Objects.isNull(token) || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = extractToken(token);

        try {
            var person = Objects.requireNonNull(
                personMsAuthFeignClient.getAuthenticatedPersonInformation("Bearer " + token)
                    .getBody()
            );

            log.info("Setting up security context: {}", person);
            authenticate(person);

            filterChain.doFilter(request, response);
        } catch (BusinessException be) {
            filterExceptionUtil.setResponseError(response, be);
        }
    }

    private void authenticate(final GetAuthenticatedPersonInformationResult person) {
        var mappedPerson = AuthenticatedPersonMapper.convertToAuthenticatedPerson(person);
        var authentication = new UsernamePasswordAuthenticationToken(
            mappedPerson,
            null,
            mappedPerson.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String extractToken(String token) {
        if (ObjectUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
            throw new NoTokenReceivedException();
        }
        return token.substring(7);
    }
}
