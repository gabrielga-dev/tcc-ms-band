package br.com.events.band.data.model;

import br.com.events.band.data.io.file.FileDTO;

import java.time.LocalDateTime;

public interface TableWithProfilePicture {

    String getProfilePictureUuid();

    void setProfilePictureUuid(String uuid);

    void setUpdateDate(LocalDateTime updateDate);

    default void removeProfilePicture() {
        this.setProfilePictureUuid(null);
        this.setUpdateDate(LocalDateTime.now());
    }

    default void setProfilePicture(FileDTO file) {
        this.setProfilePictureUuid(file.getUuid());
        this.setUpdateDate(LocalDateTime.now());
    }
}
