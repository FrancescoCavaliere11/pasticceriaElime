package org.elime.elimebackend.service.interfaces;

public interface BlacklistService {
    boolean isTokenBlackListed(String token);
}
