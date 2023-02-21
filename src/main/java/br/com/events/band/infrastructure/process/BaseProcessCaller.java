package br.com.events.band.infrastructure.process;

public interface BaseProcessCaller<T, R> {

    R callProcesses(T param);
}
