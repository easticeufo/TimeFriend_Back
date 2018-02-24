package com.madongfang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.CashFlowStatement;
import com.madongfang.entity.StatementPK;

public interface CashFlowStatementRepository extends JpaRepository<CashFlowStatement, StatementPK> {

}
