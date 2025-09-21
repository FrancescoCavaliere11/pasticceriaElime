package org.elime.elimebackend.service.interfaces;

import org.elime.elimebackend.data.dto.create.CustomerCreateDto;
import org.elime.elimebackend.data.dto.response.AuthResponseDto;

public interface AuthService {
    AuthResponseDto signIn(String emailOrTelephoneNumber, String password);
    void signUp(CustomerCreateDto customerCreateDto);
}
