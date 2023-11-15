package br.com.events.band.older.infrastructure.process;

/**
 * This interface will be implemented by classes to execute a chain of micro processes but with void return
 *
 * @param <T> the class that will be passed as parameter
 * @author Gabriel Guimarães de Almeida
 */
public interface BaseVoidReturnProcessCaller<T> {

    void callProcesses(T param);
}
