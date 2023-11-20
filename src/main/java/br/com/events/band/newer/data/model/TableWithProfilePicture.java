package br.com.events.band.newer.data.model;

import java.time.LocalDateTime;

public interface TableWithProfilePicture {

    String getProfilePictureUuid();

    void setProfilePictureUuid(String uuid);

    void setUpdateDate(LocalDateTime updateDate);

    default void removeProfilePicture() {
        this.setProfilePictureUuid(null);
        this.setUpdateDate(LocalDateTime.now());
    }

    default void setProfilePicture(String uuid) {
        this.setProfilePictureUuid(uuid);
        this.setUpdateDate(LocalDateTime.now());
    }
}
