package br.com.events.band.infrastructure.process;

/**
 * This interface will be implemented by classes to execute a chain of micro processes
 *
 * @param <T> the class that will be passed as parameter
 * @param <R> the class that will be returned
 * @author Gabriel Guimarães de Almeida
 */
public interface BaseProcessCaller<T, R> {

    R callProcesses(T param);
}
