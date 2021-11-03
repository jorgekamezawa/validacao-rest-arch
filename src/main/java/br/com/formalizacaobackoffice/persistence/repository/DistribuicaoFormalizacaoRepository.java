package br.com.formalizacaobackoffice.persistence.repository;

import br.com.formalizacaobackoffice.persistence.entity.DistribuicaoFormalizacaoEntity;
import br.com.formalizacaobackoffice.persistence.entity.TipoFormalizacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistribuicaoFormalizacaoRepository extends JpaRepository<DistribuicaoFormalizacaoEntity, Long> {
    @Query("select d from DistribuicaoFormalizacaoEntity d where d.tipoFormalizacaoEntity = :tipoFormalizacaoEntity " +
            "and d.porcentagemDeDistribuicao > :porcentagemMinima")
    List<DistribuicaoFormalizacaoEntity> buscarDistribuicaoPorTipoFormalizacaoEPorcentagemMaiorQue(@Param("tipoFormalizacaoEntity") TipoFormalizacaoEntity tipoFormalizacaoEntity,
                                                                                                   @Param("porcentagemMinima") double porcentagemMinima);

    List<DistribuicaoFormalizacaoEntity> findAllByTipoFormalizacaoEntityAndPorcentagemDeDistribuicaoGreaterThan(TipoFormalizacaoEntity tipoFormalizacaoEntity,
                                                                                                                double porcentagemMinima);
}
