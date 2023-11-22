package br.com.events.band.adapter.port.rest.config.filters;

import br.com.events.band.adapter.feign.MsAuthFeign;
import br.com.events.band.core.exception.BusinessException;
import br.com.events.band.core.util.FilterExceptionUtil;
import br.com.events.band.data.io.auth.AuthenticatedPerson;
import br.com.events.band.adapter.port.rest.config.filters.exception.NoTokenReceivedException;
import br.com.events.band.data.io.person.response.PersonResponse;
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

    private final MsAuthFeign personMsAuthFeignClient;
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
            var person = personMsAuthFeignClient.getAuthenticatedPersonInformation("Bearer " + token);

            log.info("Setting up security context: {}", person);
            authenticate(person);

            filterChain.doFilter(request, response);
        } catch (BusinessException be) {
            filterExceptionUtil.setResponseError(response, be);
        }
    }

    private void authenticate(final PersonResponse person) {
        var mappedPerson = new AuthenticatedPerson(person);
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
