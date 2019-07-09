package org.mbonilla.wallet.service;

import org.mbonilla.wallet.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
