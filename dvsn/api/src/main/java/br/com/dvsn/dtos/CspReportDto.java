package br.com.dvsn.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CspReportDto {

    @JsonProperty("blocked-uri")
    private String blockedUri;

    @JsonProperty("column-number")
    private int columnNumber;

    private String disposition;

    @JsonProperty("document-uri")
    private String documentUri;

    @JsonProperty("effective-directive")
    private String effectiveDirective;

    @JsonProperty("line-number")
    private int lineNumber;

    @JsonProperty("original-policy")
    private String originalPolicy;

    private String referrer;

    @JsonProperty("source-file")
    private String sourceFile;

    @JsonProperty("status-code")
    private int statusCode;

    @JsonProperty("violated-directive")
    private String violatedDirective;

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
}