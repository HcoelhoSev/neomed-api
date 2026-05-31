package br.com.neomed.api.domain.auth.service;

import br.com.neomed.api.domain.auth.LoginRequestDTO;
import br.com.neomed.api.domain.auth.LoginResponseDTO;
import br.com.neomed.api.domain.auth.RefreshTokenRequestDTO;
import br.com.neomed.api.domain.auth.RegisterRequestDTO;
import br.com.neomed.api.domain.auth.ForgotPasswordRequestDTO;
import br.com.neomed.api.domain.auth.ResetPasswordRequestDTO;

public interface AuthService {
    void register(RegisterRequestDTO dto);
    LoginResponseDTO login(LoginRequestDTO dto);
    LoginResponseDTO refresh(RefreshTokenRequestDTO dto);
    void forgotPassword(ForgotPasswordRequestDTO dto);
    void resetPassword(ResetPasswordRequestDTO dto);
}