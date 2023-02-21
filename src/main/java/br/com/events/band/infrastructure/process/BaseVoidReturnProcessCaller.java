package br.com.events.band.infrastructure.process;

public interface BaseVoidReturnProcessCaller<T> {

    void callProcesses(T param);
}
