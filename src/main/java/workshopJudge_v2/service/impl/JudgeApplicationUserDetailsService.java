package workshopJudge_v2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import workshopJudge_v2.model.entity.Role;
import workshopJudge_v2.model.entity.UserEntity;
import workshopJudge_v2.repository.UserEntityRepository;

@Service
public class JudgeApplicationUserDetailsService implements UserDetailsService {

    private final UserEntityRepository userRepository;

    @Autowired
    public JudgeApplicationUserDetailsService(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepository.findByUsername(username)
                .map(this::mapUserDetails)
                .orElseThrow(() ->
                        new UsernameNotFoundException("No such User with username: " + username + " !"));


    }

    private UserDetails mapUserDetails(UserEntity userEntity) {
        return User
                .withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(grantedAuthority(userEntity.getRole()))
                .build();
    }

    private GrantedAuthority grantedAuthority(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getName().name());
    }
}
