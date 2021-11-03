package br.com.formalizacaobackoffice.persistence.repository;

import br.com.formalizacaobackoffice.model.Origem;
import br.com.formalizacaobackoffice.model.Processo;
import br.com.formalizacaobackoffice.model.Segmentacao;
import br.com.formalizacaobackoffice.persistence.entity.OrigemEntity;
import br.com.formalizacaobackoffice.persistence.entity.ProcessoEntity;
import br.com.formalizacaobackoffice.persistence.entity.SegmentacaoEntity;
import br.com.formalizacaobackoffice.persistence.entity.TipoFormalizacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoFormalizacaoRepository extends JpaRepository<TipoFormalizacaoEntity, Long> {
    Optional<TipoFormalizacaoEntity> findByProcessoEntityAndOrigemEntityAndSegmentacaoEntity(ProcessoEntity processo, OrigemEntity origem, SegmentacaoEntity segmentacao);
}
