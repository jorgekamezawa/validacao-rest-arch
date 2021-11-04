//package br.com.formalizacaobackoffice.persistence.entity;
//
//import lombok.Data;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import java.util.Date;
//
//@Data
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public abstract class Auditable<U> {
//
//    @CreatedBy
//    protected U createdBy;
//
//    @LastModifiedBy
//    protected U lastModifiedBy;
//
//    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    protected Date dataHoraCriacao;
//
//    @LastModifiedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    protected Date dataHoraUltimaAtualizacao;
//}
