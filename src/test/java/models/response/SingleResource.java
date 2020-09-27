package models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SingleResource {
    @JsonProperty("data")
    private SingleResourceData data;

    @JsonProperty("ad")
    private SingleResourceAd ad;

}
