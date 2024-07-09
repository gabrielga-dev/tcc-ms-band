package br.com.events.band.business.service.impl;

import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.util.AuthUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean isAuthenticated() {
        return AuthUtil.isAuthenticated();
    }

    @Override
    public String getAuthenticatedPersonUuid() {
        return AuthUtil.getAuthenticatedPersonUuid();
    }
}
