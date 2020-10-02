package pl.sda.springbootdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.springbootdata.domain.AppUser;
import pl.sda.springbootdata.repository.AppUserRepository;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        AppUser user = repository.findByNickName(nickname);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User " + nickname + " not found.");
    }
}
