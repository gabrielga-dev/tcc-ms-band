package br.com.events.band.adapter.repository;

import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;

public interface QuoteRequestRepository {

    QuoteRequestTable save(QuoteRequestTable toSave);
}
