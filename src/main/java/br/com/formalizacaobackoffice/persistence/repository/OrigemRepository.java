package br.com.formalizacaobackoffice.persistence.repository;

import br.com.formalizacaobackoffice.persistence.entity.OrigemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrigemRepository extends JpaRepository<OrigemEntity, Long> {
    Optional<OrigemEntity> findByNomeOrigem(String nomeOrigem);
}
