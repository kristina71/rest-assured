package models.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterUser {
    @Builder.Default private String email = "fgfb";
    private String password;
}
