package com.fanqie.framework.scheduler.repo;


import com.fanqie.framework.scheduler.model.Farmer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmerRepository {
    List<Farmer> findAll();

    void save(Farmer farmer);
}
