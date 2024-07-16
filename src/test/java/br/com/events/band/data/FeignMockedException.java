package br.com.events.band.data;

import feign.FeignException;

public class FeignMockedException extends FeignException {

    public FeignMockedException(int status, String message) {
        super(status, message);
    }
}
