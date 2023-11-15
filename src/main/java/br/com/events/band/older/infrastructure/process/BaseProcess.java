package br.com.events.band.older.infrastructure.process;

/**
 * This interface will be implemented by classes to execute a micro process that will be part of a bigger chain
 *
 * @param <T> the class that will be passed as parameter
 * @param <R> the class that will be returned
 * @author Gabriel Guimarães de Almeida
 */
public interface BaseProcess<T, R> {

    default boolean matches(T param){
        return true;
    }

    R execute(T param);
}
