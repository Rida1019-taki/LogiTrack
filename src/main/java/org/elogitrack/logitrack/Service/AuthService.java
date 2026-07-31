package org.elogitrack.logitrack.service;

import org.elogitrack.logitrack.dto.auth.AuthResponse;
import org.elogitrack.logitrack.dto.auth.LoginRequest;
import org.elogitrack.logitrack.dto.auth.RegistreRequest;


public interface AuthService {

    AuthResponse register(RegistreRequest request);

    AuthResponse login(LoginRequest request);

}