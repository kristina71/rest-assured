package models.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SingleResourceAd {
    @Builder.Default private String company = "StatusCode Weekly";
    @Builder.Default private String url = "http://statuscode.org/";
    @Builder.Default private String text = "A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things.";
}
