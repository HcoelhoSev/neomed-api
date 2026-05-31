package br.com.neomed.api.domain.auth.service;

import br.com.neomed.api.domain.auth.ForgotPasswordRequestDTO;
import br.com.neomed.api.domain.auth.LoginRequestDTO;
import br.com.neomed.api.domain.auth.LoginResponseDTO;
import br.com.neomed.api.domain.auth.MeResponseDTO;
import br.com.neomed.api.domain.auth.RefreshTokenRequestDTO;
import br.com.neomed.api.domain.auth.RegisterRequestDTO;
import br.com.neomed.api.domain.auth.ResetPasswordRequestDTO;
import br.com.neomed.api.domain.auth.UserAuthResponseDTO;

public interface AuthService {

    MeResponseDTO me();

    LoginResponseDTO login(LoginRequestDTO request);

    void forgotPassword(ForgotPasswordRequestDTO request);

    void resetPassword(ResetPasswordRequestDTO request);

    LoginResponseDTO refreshToken(RefreshTokenRequestDTO request);

    UserAuthResponseDTO register(RegisterRequestDTO request);
}