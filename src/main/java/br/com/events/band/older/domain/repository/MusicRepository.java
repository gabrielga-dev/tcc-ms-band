package br.com.events.band.older.domain.repository;

import br.com.events.band.newer.data.table.MusicTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<MusicTable, String> {
}
