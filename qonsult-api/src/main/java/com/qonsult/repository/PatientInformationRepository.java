package com.qonsult.repository;

import com.qonsult.entity.PatientInformation;
import com.qonsult.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientInformationRepository extends GenericRepository<PatientInformation,Long> {
}
