package br.com.dvsn.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CspReportWrapperDto {

    @JsonProperty("csp-report")
    private CspReportDto cspReport;

    public CspReportDto getCspReport() {
        return cspReport;
    }

    public void setCspReport(CspReportDto cspReportDto) {
        this.cspReport = cspReportDto;
    }
}