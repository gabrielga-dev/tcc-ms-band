package br.com.events.band.domain.repository;

import br.com.events.band.domain.entity.SheetMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetMusicRepository extends JpaRepository<SheetMusic, String> {

}
