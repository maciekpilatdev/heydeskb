package pl.org.conceptweb.heydeskb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MethodResponse {

    String status;
    String message;
}
