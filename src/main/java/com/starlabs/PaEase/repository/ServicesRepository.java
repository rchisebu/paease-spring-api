
package com.starlabs.PaEase.repository;

import com.starlabs.PaEase.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chulu
 */
@Repository
public interface ServicesRepository extends JpaRepository<Services, Long>{
    
}
