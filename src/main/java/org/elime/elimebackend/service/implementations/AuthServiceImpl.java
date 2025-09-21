package org.elime.elimebackend.service.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.CustomerCreateDto;
import org.elime.elimebackend.data.dto.response.AuthResponseDto;
import org.elime.elimebackend.data.entities.Customer;
import org.elime.elimebackend.data.entities.User;
import org.elime.elimebackend.data.repository.CustomerRepository;
import org.elime.elimebackend.data.repository.UserRepository;
import org.elime.elimebackend.security.authentication.JwtHandler;
import org.elime.elimebackend.service.interfaces.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final JwtHandler jwtHandler;

    @Override
    public AuthResponseDto signIn(String emailOrTelephoneNumber, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(emailOrTelephoneNumber, password)
        );

        User user = userRepository.findByEmailOrPhoneNumber(emailOrTelephoneNumber, emailOrTelephoneNumber)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setUserId(user.getId());
        authResponseDto.setAccessToken(jwtHandler.createAccessToken(user));
        authResponseDto.setRefreshToken(jwtHandler.createRefreshToken(user));
        return authResponseDto;
    }


    @Override
    public void signUp(CustomerCreateDto customerCreateDto) {
        customerRepository.save(modelMapper.map(customerCreateDto, Customer.class));
    }
}
