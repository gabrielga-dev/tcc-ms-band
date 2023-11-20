package br.com.events.band.data.model;

import br.com.events.band.core.exception.ResourceAlreadyActivatedException;
import br.com.events.band.core.exception.ResourceAlreadyDeactivatedException;

import java.time.LocalDateTime;

public interface ActionableTable {

    Boolean getActive();

    void setActive(Boolean activationStatus);

    void setUpdateDate(LocalDateTime updateDate);

    default boolean isActive() {
        return this.getActive();
    }

    default void toggleActivity() {
        this.setActive(!this.getActive());
        this.setUpdateDate(LocalDateTime.now());
    }

    default void activate() {
        if (this.isActive()) {
            throw new ResourceAlreadyActivatedException();
        }
        this.setActive(Boolean.TRUE);
        this.setUpdateDate(LocalDateTime.now());
    }

    default void deactivate() {
        if (!this.isActive()) {
            throw new ResourceAlreadyDeactivatedException();
        }
        this.setActive(Boolean.FALSE);
        this.setUpdateDate(LocalDateTime.now());
    }
}
