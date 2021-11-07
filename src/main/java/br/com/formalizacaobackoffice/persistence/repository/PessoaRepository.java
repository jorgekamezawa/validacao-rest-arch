package br.com.formalizacaobackoffice.persistence.repository;

import br.com.formalizacaobackoffice.persistence.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, String> {
}
