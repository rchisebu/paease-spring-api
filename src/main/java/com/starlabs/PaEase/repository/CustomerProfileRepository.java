
package com.starlabs.PaEase.repository;

import com.starlabs.PaEase.model.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francis chulu
 */
@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    CustomerProfile findByMsisdn(Long msisdn);
}
