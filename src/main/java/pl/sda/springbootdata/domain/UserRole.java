package pl.sda.springbootdata.domain;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
public enum UserRole implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_MODERATOR;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
