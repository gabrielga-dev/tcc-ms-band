package br.com.events.band.infrastructure.process;

public interface BaseProcess<T, R> {

    R validate(T toValidate);
}
