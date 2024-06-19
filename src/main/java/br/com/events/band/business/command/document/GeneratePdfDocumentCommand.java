package br.com.events.band.business.command.document;

import br.com.events.band.data.io.document.PdfConfigurationDTO;

import java.util.Map;

public interface GeneratePdfDocumentCommand {

    byte[] execute(PdfConfigurationDTO configuration, Map<String, Object> params);
}
