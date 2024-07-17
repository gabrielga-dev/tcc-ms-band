package br.com.events.band.data.model.table.document;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DocumentTemplateTableMock {

    public static DocumentTemplateTable build() {
        return new DocumentTemplateTable(
                MockConstants.LONG,
                MockConstants.STRING,
                MockConstants.STRING
        );
    }
}
