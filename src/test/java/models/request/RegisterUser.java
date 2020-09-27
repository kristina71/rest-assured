package models.request;

import lombok.Builder;
import lombok.Getter;

import static utils.RandomUtils.getRandomEmail;

@Getter
@Builder
public class RegisterUser {
    @Builder.Default private String email = getRandomEmail();
    private String password;
}
