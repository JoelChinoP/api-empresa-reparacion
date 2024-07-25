package emp.rep.api.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AuthCredentials {

    private String user;
    private String password;

    public String getUserName() {
        return user;
    }
}
