package br.com.events.band.older.infrastructure.process;

/**
 * This interface will be implemented by classes to execute a micro process that will be part of a bigger chain but with
 * a void return
 *
 * @param <T> the class that will be passed as parameter
 * @author Gabriel Guimarães de Almeida
 */
public interface BaseVoidReturnProcess<T> {

    default boolean matches(T param){
        return true;
    }

    void validate(T toValidate);
}
