package pl.com.conceptweb.uniappbackend.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResponseWrapper {

    public String message;
    public List result;
}
