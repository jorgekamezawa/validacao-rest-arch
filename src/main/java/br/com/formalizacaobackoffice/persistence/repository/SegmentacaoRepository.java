package br.com.formalizacaobackoffice.persistence.repository;

import br.com.formalizacaobackoffice.persistence.entity.SegmentacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SegmentacaoRepository extends JpaRepository<SegmentacaoEntity, Long> {

    Optional<SegmentacaoEntity> findByNomeSegmentacao(String nomeSegmentacao);
}
