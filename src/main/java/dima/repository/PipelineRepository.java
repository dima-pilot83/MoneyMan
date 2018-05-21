package dima.repository;

import dima.entity.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PipelineRepository extends JpaRepository<Pipeline, Long> {
    @Query("SELECT p FROM Pipeline p where p.name = :name")
    Pipeline findByLogin(@Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pipeline p WHERE p.name = :name")
    boolean existsByLogin(@Param("name") String name);
}
