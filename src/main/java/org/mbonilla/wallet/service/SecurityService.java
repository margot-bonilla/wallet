package org.mbonilla.wallet.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
