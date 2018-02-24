package com.madongfang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madongfang.entity.BalanceSheet;
import com.madongfang.entity.StatementPK;

public interface BalanceSheetRepository extends JpaRepository<BalanceSheet, StatementPK> {

}
