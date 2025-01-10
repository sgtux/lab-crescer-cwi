package br.com.dvsn.repository;

import br.com.dvsn.entities.CspReport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CspReportRepository extends CrudRepository<CspReport, Long> {

}