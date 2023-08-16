package clara.correa.scholarship.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomResponse {
    private boolean success;
    private String message;
}

