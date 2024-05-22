package br.com.events.band.business.command.document.quote_request;

import br.com.events.band.business.command.document.FindDocumentTemplateCommand;
import br.com.events.band.business.command.document.GeneratePdfDocumentCommand;
import br.com.events.band.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.band.data.io.pdf.PdfDTO;
import br.com.events.band.data.io.pdf.PdfType;
import br.com.events.band.data.model.table.document.DocumentTemplateTable;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@RequiredArgsConstructor
public abstract class GenerateQuoteRequestPdfCommand {

    @Autowired
    private FindQuoteRequestCommand findQuoteRequestCommand;

    @Autowired
    private FindDocumentTemplateCommand findDocumentTemplateCommand;

    @Autowired
    protected GeneratePdfDocumentCommand generatePdfDocumentCommand;

    protected QuoteRequestTable findQuoteRequest(String quoteRequestUuid) {
        return findQuoteRequestCommand.findByUuidOrThrow(quoteRequestUuid);
    }

    protected DocumentTemplateTable findTemplate() {
        return findDocumentTemplateCommand.findByIdOrThrow(getAcceptedPdfType().getId());
    }

    public abstract PdfType getAcceptedPdfType();

    public boolean isAccepted(PdfType pdfType) {
        return Objects.equals(getAcceptedPdfType(), pdfType);
    }

    public abstract PdfDTO execute(String quoteRequestUuid);
}
