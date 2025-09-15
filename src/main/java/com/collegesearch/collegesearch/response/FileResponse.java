package com.collegesearch.collegesearch.response;

public class FileResponse {
    private String filename;
    private String fileDownloadUri;
    private String fileType;
    private Long size;
    public FileResponse(String filename, String fileDownloadUri, String fileType, Long size) {
        super();
        this.filename=filename;
        this.fileDownloadUri=fileDownloadUri;
        this.fileType=fileType;
        this.size=size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileDownloadUrl() {
        return fileDownloadUri;
    }

    public void setFileDownloadUrl(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
