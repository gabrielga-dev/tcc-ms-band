package br.com.events.band.infrastructure.process;

public interface BaseVoidReturnProcess<T> {

    void validate(T toValidate);
}
