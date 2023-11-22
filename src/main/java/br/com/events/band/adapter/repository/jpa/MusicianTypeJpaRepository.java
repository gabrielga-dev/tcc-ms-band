package br.com.events.band.adapter.repository.jpa;

import br.com.events.band.adapter.repository.MusicianTypeRepository;
import br.com.events.band.data.model.table.musician.MusicianTypeTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicianTypeJpaRepository extends MusicianTypeRepository, JpaRepository<MusicianTypeTable, String> {

    @Query("SELECT musicianType FROM MusicianTypeTable musicianType WHERE musicianType.uuid IN :uuids")
    List<MusicianTypeTable> findAllByUuid(@Param("uuids") List<String> uuids);

    @Query(
            "SELECT musicianType " +
                    "FROM MusicianTypeTable musicianType " +
                    "WHERE ((:name IS NULL) OR  (musicianType.name LIKE CONCAT('%',:name,'%')))"
    )
    Page<MusicianTypeTable> findByCriteria(@Param("name") String name, Pageable pageable);
}
