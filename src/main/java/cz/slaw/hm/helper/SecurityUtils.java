package cz.slaw.hm.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.slaw.hm.dto.Error;
import cz.slaw.hm.dto.ResponseError;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sławek on 2016-12-07.
 */
public final class SecurityUtils {


    private static final ObjectMapper mapper = new ObjectMapper();


    private SecurityUtils() {
    }

    public static void sendError(HttpServletResponse response, Exception exception, int status, String message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(status);
        PrintWriter writer = response.getWriter();
        Error error = new Error("authError", exception.getMessage());
        writer.write(mapper.writeValueAsString(new ResponseError(status, message, error)));
        writer.flush();
        writer.close();
    }


    public static void sendResponse(HttpServletResponse response, int status, Object object) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = response.getWriter();
        writer.write(mapper.writeValueAsString(object));
        response.setStatus(status);
        writer.flush();
        writer.close();
    }

}