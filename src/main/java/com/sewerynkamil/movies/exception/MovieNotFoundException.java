package com.sewerynkamil.movies.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + MovieNotFoundException.NAMESPACE_URI + "}custom_fault",
        faultStringOrReason = "@faultString")
public class MovieNotFoundException extends Exception {
    public static final String NAMESPACE_URI = "http://sewerynkamil.pl/movies";

    public MovieNotFoundException(String message) {
        super(message);
    }
}
