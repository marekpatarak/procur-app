package com.pproject.app.repository;

import com.pproject.app.entity.BusinessEntity;
import org.springframework.data.repository.CrudRepository;

public interface BusinessEntityRepository extends CrudRepository<BusinessEntity, Integer> {
}
