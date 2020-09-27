package models.request;

import lombok.Builder;
import lombok.Getter;

import static utils.RandomUtils.getRandomString;

@Getter
@Builder
public class CreateUser {
    @Builder.Default private String name = getRandomString(6);
    @Builder.Default private String leader = "leader";
}
