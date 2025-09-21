package org.elime.elimebackend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.repository.BlacklistRepository;
import org.elime.elimebackend.service.interfaces.BlacklistService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlacklistServiceImpl implements BlacklistService {
    private final BlacklistRepository blacklistRepository;

    @Override
    public boolean isTokenBlackListed(String token) {
        return blacklistRepository.existsByToken(token);
    }
}
