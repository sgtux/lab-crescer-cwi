package br.com.dvsn.entities;

import java.util.Date;

import br.com.dvsn.dtos.CspReportDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class CspReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "csp_report")
    @SequenceGenerator(name = "csp_report", sequenceName = "csp_report_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "blocked_uri")
    private String blockedUri;

    @Column(name = "column_number")
    private int columnNumber;

    @Column(name = "disposition")
    private String disposition;

    @Column(name = "document_uri")
    private String documentUri;

    @Column(name = "effective_directive")
    private String effectiveDirective;

    @Column(name = "line_number")
    private int lineNumber;

    @Column(name = "original_policy")
    private String originalPolicy;

    @Column(name = "referrer")
    private String referrer;

    @Column(name = "source_file")
    private String sourceFile;

    @Column(name = "status_code")
    private int statusCode;

    @Column(name = "violated_directive")
    private String violatedDirective;

    @Column(name = "date")
    private Date date;

    public CspReport() {
    }

    public CspReport(CspReportDto dto) {
        blockedUri = dto.getBlockedUri();
        columnNumber = dto.getColumnNumber();
        disposition = dto.getDisposition();
        documentUri = dto.getDocumentUri();
        effectiveDirective = dto.getEffectiveDirective();
        lineNumber = dto.getLineNumber();
        originalPolicy = dto.getOriginalPolicy();
        referrer = dto.getReferrer();
        sourceFile = dto.getSourceFile();
        statusCode = dto.getStatusCode();
        violatedDirective = dto.getViolatedDirective();
        date = new Date();
    }

    public String getBlockedUri() {
        return this.blockedUri;
    }

    public void setBlockedUri(String blockedUri) {
        this.blockedUri = blockedUri;
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getDocumentUri() {
        return this.documentUri;
    }

    public void setDocumentUri(String documentoUri) {
        this.documentUri = documentoUri;
    }

    public String getEffectiveDirective() {
        return this.effectiveDirective;
    }

    public void setEffectiveDirective(String effectiveDirective) {
        this.effectiveDirective = effectiveDirective;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getOriginalPolicy() {
        return this.originalPolicy;
    }

    public void setOriginalPolicy(String originalPolicy) {
        this.originalPolicy = originalPolicy;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getSourceFile() {
        return this.sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getViolatedDirective() {
        return this.violatedDirective;
    }

    public void setViolatedDirective(String violatedDirective) {
        this.violatedDirective = violatedDirective;
    }

    public Date getCriadoEm() {
        return date;
    }

    public void setCriadoEm(Date date) {
        this.date = date;
    }
}