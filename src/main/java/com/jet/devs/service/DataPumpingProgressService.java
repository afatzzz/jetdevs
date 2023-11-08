package com.jet.devs.service;

import com.jet.devs.model.DataPumpingProgress;

import java.util.List;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
public interface DataPumpingProgressService {

    List<DataPumpingProgress> getAllUploadedData();

    void deleteFile(String fileName);
}
