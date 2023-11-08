package com.jet.devs.service.impl;

import com.jet.devs.helper.ExcelHelper;
import com.jet.devs.model.AuditTrail;
import com.jet.devs.model.DataExcel;
import com.jet.devs.model.DataPumpingProgress;
import com.jet.devs.repository.AuditTrailRepository;
import com.jet.devs.repository.DataExcelRepository;
import com.jet.devs.repository.DataPumpingProgressRepository;
import com.jet.devs.service.FileProcessingService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Author: ab
 * Date Time: 07/11/23
 */

@Service
public class FileProcessingServiceImpl implements FileProcessingService {

    private final DataPumpingProgressRepository dataPumpingProgressRepository;
    private final DataExcelRepository dataExcelRepository;
    private final AuditTrailRepository auditTrailRepository;

    public FileProcessingServiceImpl(DataPumpingProgressRepository dataPumpingProgressRepository,
                                     DataExcelRepository dataExcelRepository,
                                     AuditTrailRepository auditTrailRepository) {
        this.dataPumpingProgressRepository = dataPumpingProgressRepository;
        this.dataExcelRepository = dataExcelRepository;
        this.auditTrailRepository = auditTrailRepository;
    }

    @Override
    public void processFile(MultipartFile file, String username) throws IOException {
        List<DataExcel> dataExcels = ExcelHelper.excelToTutorials(file.getInputStream(), file.getOriginalFilename());
        dataExcelRepository.saveAll(dataExcels);

        DataPumpingProgress dataPumpingProgress = new DataPumpingProgress();
        dataPumpingProgress.setCreatedAt(new Date());
        dataPumpingProgress.setExcelName(file.getOriginalFilename());
        dataPumpingProgress.setGeneratedBy(username);
        dataPumpingProgressRepository.save(dataPumpingProgress);
    }

    @Override
    public List<DataExcel> getRecordByFileName(String fileName, String username) {
        DataPumpingProgress dataPumpingProgress =
                dataPumpingProgressRepository.findDataPumpingProgressByExcelName(fileName);
        if (dataPumpingProgress == null) {
            return null;
        } else {
            dataPumpingProgress.setViewBy(username);
            dataPumpingProgress.setLastView(new Date());
            dataPumpingProgressRepository.save(dataPumpingProgress);
        }

        AuditTrail auditTrail = AuditTrail.builder()
                .fileName(fileName)
                .timeLog(new Date())
                .username(username).build();
        auditTrailRepository.save(auditTrail);

        return dataExcelRepository.findDataExcelByFileName(fileName);
    }

    @Override
    public void deleteByFileName(String fileName) {
        dataExcelRepository.deleteDataExcelByFileName(fileName);
    }
}
