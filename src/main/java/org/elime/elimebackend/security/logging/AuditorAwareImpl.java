package org.elime.elimebackend.security.logging;

import io.micrometer.common.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.service.implementations.UserDetailServiceImpl;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NonNullApi
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {
    private final UserDetailServiceImpl userDetailService;

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            return Optional.of(userDetailService.getCurrentUserEmail());
        } catch (Exception e) {
            return Optional.of("system");
        }
    }
}
