package com.jet.devs.controller;

import com.jet.devs.bean.DeleteBean;
import com.jet.devs.bean.ResponseBean;
import com.jet.devs.helper.ExcelHelper;
import com.jet.devs.model.DataExcel;
import com.jet.devs.model.DataPumpingProgress;
import com.jet.devs.service.DataPumpingProgressService;
import com.jet.devs.service.FileProcessingService;
import com.jet.devs.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
@Slf4j
@RestController
@RequestMapping("/api/jet/devs")
public class ProcessingController {

    private final UsersService usersService;
    private final FileProcessingService fileProcessingService;
    private final DataPumpingProgressService dataPumpingProgressService;

    public ProcessingController(UsersService usersService,
                                FileProcessingService fileProcessingService,
                                DataPumpingProgressService dataPumpingProgressService) {
        this.usersService = usersService;
        this.fileProcessingService = fileProcessingService;
        this.dataPumpingProgressService = dataPumpingProgressService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseBean> processFile(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("username") String username) {
        String message = "";

        boolean isAdmin = usersService.isUserAdmin(username);

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                if (!isAdmin) {
                    message = "You don't have the privileges to upload the file. Thank you.";
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseBean(message));
                } else {
                    fileProcessingService.processFile(file, username);
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseBean(message));
                }
            } catch (Exception e) {
                log.error("ERROR: "+e.getMessage(), e.getMessage());
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseBean(message));
            }
        }

        message = "Not an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBean(message));
    }

    @GetMapping("/files")
    public ResponseEntity<List<DataPumpingProgress>> listUploadFile() {
        String message = "";
        List<DataPumpingProgress> progresses = dataPumpingProgressService.getAllUploadedData();

        if (progresses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(progresses, HttpStatus.OK);
    }

    @GetMapping("/files/data")
    public ResponseEntity<?> getSpecificFile(@RequestParam(name = "f") String filename,
                                             @RequestParam(name = "u") String username) {
        if (String.valueOf(filename).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBean("Please describe the filename"));
        }
        List<DataExcel> dataExcels = fileProcessingService.getRecordByFileName(filename, username);
        if (dataExcels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dataExcels, HttpStatus.OK);
    }

    @PostMapping("/delete/files")
    public ResponseEntity<ResponseBean> deleteFile(@RequestBody DeleteBean deleteBean) {
        if (String.valueOf(deleteBean.getUsername()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBean("Please describe the username"));
        }

        fileProcessingService.deleteByFileName(deleteBean.getFileName());
        dataPumpingProgressService.deleteFile(deleteBean.getFileName());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBean("Delete process done"));

    }
}
