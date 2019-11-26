package com.starlabs.PaEase.repository;

import com.starlabs.PaEase.model.Transactions;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francis chulu
 */
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    @Query(value = "SELECT * FROM transactions where msisdn=:msisdn AND status_code=:statusCode AND MONTH(date_created) = MONTH(CURDATE()) AND YEAR(date_created) = YEAR(CURDATE()) ORDER BY date_created ASC",nativeQuery = true)
    List<Transactions> findByMsisdnAndStatusCode(@Param("msisdn") Long msisdn,@Param("statusCode") int statusCode);
}
