package br.com.events.band.data.model;

public interface ActionableTable {

    Boolean getActive();

    void setActive(Boolean activationStatus);

    default boolean isActive() {
        return this.getActive();
    }

    default void toggleActivity() {
        this.setActive(!this.getActive());
    }
}
