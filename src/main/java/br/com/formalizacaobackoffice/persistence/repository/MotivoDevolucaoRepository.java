package br.com.formalizacaobackoffice.persistence.repository;

import br.com.formalizacaobackoffice.persistence.entity.MotivoDevolucaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotivoDevolucaoRepository extends JpaRepository<MotivoDevolucaoEntity, Long> {
    Optional<MotivoDevolucaoEntity> findByNomeMotivoDevolucao(String nomeMotivoDevolucao);
}
