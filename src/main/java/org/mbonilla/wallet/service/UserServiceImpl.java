package org.mbonilla.wallet.service;

import org.mbonilla.wallet.model.Role;
import org.mbonilla.wallet.model.User;
import org.mbonilla.wallet.repository.RoleRepository;
import org.mbonilla.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Optional<Role> userRole = roleRepository.findById(Role.ROLE_USER_ID);
        userRole.ifPresent(user::addRole);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
