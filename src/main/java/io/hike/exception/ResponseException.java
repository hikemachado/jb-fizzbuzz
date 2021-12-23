package io.hike.exception;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.validation.ValidationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class ResponseException implements ResponseExceptionMapper<Exception>{

    @Override
    public Exception toThrowable(Response response) {
        int status = response.getStatus();
        response.close();
        String msg = getBody(response);

        RuntimeException re ;
        switch (status) {
            case 450: re = new ValidationException(msg);
            case 400: re = new BadRequestException(msg);
            break;
            default:
            re = new WebApplicationException(status);       
        }
        return re;
    }

    private String getBody(Response response) {
        ByteArrayInputStream is = (ByteArrayInputStream) response.getEntity();
        byte[] bytes = new byte[is.available()];
        is.read(bytes,0,is.available());
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String body = new String(bytes);
        return body;
    }
    
}
