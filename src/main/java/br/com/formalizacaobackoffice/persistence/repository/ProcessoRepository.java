package br.com.formalizacaobackoffice.persistence.repository;

import br.com.formalizacaobackoffice.persistence.entity.ProcessoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<ProcessoEntity, Long> {

    Optional<ProcessoEntity> findByNomeProcesso(String nomeProcesso);
}
