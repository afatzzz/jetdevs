package com.jet.devs.repository;

import com.jet.devs.model.DataExcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
@Repository
public interface DataExcelRepository extends JpaRepository<DataExcel, Long> {

    List<DataExcel> findDataExcelByFileName(String fileName);

    DataExcel deleteDataExcelByFileName(String fileName);
}
