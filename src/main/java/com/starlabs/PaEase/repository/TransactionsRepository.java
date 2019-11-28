package com.starlabs.PaEase.repository;

import com.starlabs.PaEase.model.Statement;
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

    @Query(value = "SELECT * FROM transactions where msisdn=:msisdn AND status_code=:statusCode AND MONTH(date_created) = MONTH(CURDATE()) AND YEAR(date_created) = YEAR(CURDATE()) ORDER BY date_created ASC", nativeQuery = true)
    List<Transactions> findByMsisdnAndStatusCode(@Param("msisdn") Long msisdn, @Param("statusCode") int statusCode);

    @Query(value = "SELECT s.service_name as service,SUM(t.amount) as amount,cp.percentage_charge as charge FROM customerprofiles cp INNER JOIN transactions t ON cp.msisdn=t.msisdn INNER JOIN services s ON t.serviceID=s.serviceID where t.msisdn=:msisdn AND t.status_code=:statusCode AND MONTH(t.date_created) = MONTH(CURDATE()) AND YEAR(t.date_created) = YEAR(CURDATE()) group by s.service_name", nativeQuery = true)
    public List<Statement> statement(@Param("msisdn") Long msisdn, @Param("statusCode") int statusCode);

    Transactions findByStatuscodeAndTransactionIDAndMsisdnAndAccountAndServiceID(int statuscode,Long transactionID, Long msisdn, Long account, Long serviceID);

    Transactions findByTransactionID(Long transactionID);
}
