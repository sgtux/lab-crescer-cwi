package br.com.dvsn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dvsn.dtos.CspReportWrapperDto;
import br.com.dvsn.entities.CspReport;
import br.com.dvsn.helpers.StringHelper;
import br.com.dvsn.repository.CspReportRepository;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/csp")
public class CspController extends BaseController {

    @Autowired
    private CspReportRepository cspReportRepository;

    @PostMapping("report")
    public ResponseEntity<?> saveReport(HttpServletRequest request, @RequestBody String requestBody) {

        var cspReportWrapper = StringHelper.fromJson(requestBody, CspReportWrapperDto.class);
        var cspReport = cspReportWrapper.getCspReport();

        cspReportRepository.save(new CspReport(cspReport));

        return ResponseEntity.ok().build();
    }
}