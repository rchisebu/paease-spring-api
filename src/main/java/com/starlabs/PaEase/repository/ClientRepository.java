package com.starlabs.PaEase.repository;

import com.starlabs.PaEase.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chulu
 */
@Repository
public interface ClientRepository extends JpaRepository<Clients, Long>{
    
}
