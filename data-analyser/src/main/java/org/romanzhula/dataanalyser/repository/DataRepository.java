package org.romanzhula.dataanalyser.repository;

import org.romanzhula.dataanalyser.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
}
