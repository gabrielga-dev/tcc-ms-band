package br.com.events.band.business.use_case.quote_request;

import br.com.events.band.data.io.pdf.PdfDTO;
import br.com.events.band.data.io.pdf.PdfType;

public interface GenerateQuoteRequestPdfUseCase {

    PdfDTO execute(String quoteRequestUuid, PdfType pdfType);
}
