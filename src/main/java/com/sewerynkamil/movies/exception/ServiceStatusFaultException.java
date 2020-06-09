package com.sewerynkamil.movies.exception;

import com.sewerynkamil.movies.movie.ServiceStatus;

public class ServiceStatusFaultException extends RuntimeException {
    private ServiceStatus serviceStatus;

    public ServiceStatusFaultException(String message, ServiceStatus serviceStatus) {
        super(message);
        this.serviceStatus = serviceStatus;
    }

    public ServiceStatusFaultException(String message, Throwable e, ServiceStatus serviceStatus) {
        super(message, e);
        this.serviceStatus = serviceStatus;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
