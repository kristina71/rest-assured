package models.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUser {
    @Builder.Default private String name = "fgfb";
    @Builder.Default private String leader = "leader";
}
