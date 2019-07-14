package org.mbonilla.wallet;

import org.mbonilla.wallet.model.Role;
import org.mbonilla.wallet.model.User;
import org.mbonilla.wallet.repository.RoleRepository;
import org.mbonilla.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${user.admin.username}")
    private String adminName;

    @Value("${user.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        User admin = userRepository.findByUsername(adminName);
        if (admin == null) {
            admin = new User();
            admin.setUsername(adminName);
            admin.setPassword(bCryptPasswordEncoder.encode(adminPassword));
            Optional<Role> userRole = roleRepository.findById(Role.ROLE_ADMIN_ID);
            userRole.ifPresent(admin::addRole);
            userRepository.save(admin);
        }
    }
}
