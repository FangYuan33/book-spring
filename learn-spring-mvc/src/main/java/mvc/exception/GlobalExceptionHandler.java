package mvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(HttpServletRequest request, HttpServletResponse response, RuntimeException e) {
        e.printStackTrace();
        request.setAttribute("message", e.getMessage());
        return "error";
    }
}
