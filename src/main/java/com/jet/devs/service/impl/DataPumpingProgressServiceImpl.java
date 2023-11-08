package com.jet.devs.service.impl;

import com.jet.devs.model.DataPumpingProgress;
import com.jet.devs.repository.DataPumpingProgressRepository;
import com.jet.devs.service.DataPumpingProgressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
@Service
public class DataPumpingProgressServiceImpl implements DataPumpingProgressService {
    public final DataPumpingProgressRepository dataPumpingProgressRepository;

    public DataPumpingProgressServiceImpl(DataPumpingProgressRepository dataPumpingProgressRepository) {
        this.dataPumpingProgressRepository = dataPumpingProgressRepository;
    }

    @Override
    public List<DataPumpingProgress> getAllUploadedData() {
        return dataPumpingProgressRepository.findAll();
    }

    @Override
    public void deleteFile(String fileName) {
        dataPumpingProgressRepository.deleteDataPumpingProgressByExcelName(fileName);
    }
}
