package com.jet.devs.service;

import com.jet.devs.model.DataExcel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
public interface FileProcessingService {

    void processFile(MultipartFile file, String username) throws IOException;
    List<DataExcel> getRecordByFileName(String fileName, String username);

    void deleteByFileName(String fileName);
}
