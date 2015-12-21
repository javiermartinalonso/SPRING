package es.jmartin.ejemplos.spring.data.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jmartin.ejemplos.spring.data.model.domainobject.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long>
{

}
