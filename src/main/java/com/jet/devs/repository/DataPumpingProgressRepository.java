package com.jet.devs.repository;

import com.jet.devs.model.DataPumpingProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
@Repository
public interface DataPumpingProgressRepository extends JpaRepository<DataPumpingProgress, Long> {

    DataPumpingProgress findDataPumpingProgressByExcelName(String excelName);

    int deleteDataPumpingProgressByExcelName(String excelName);
}
