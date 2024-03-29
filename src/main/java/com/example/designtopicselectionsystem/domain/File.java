package com.example.designtopicselectionsystem.domain;

public class File {

    private Integer id;
    private String file_id;
    private Integer topic_id;
    private String filename;
    private String filePath; // 文件路径

    public File() {

    }

    public File(String file_id, Integer topic_id, String filename, String filePath) {
        this.file_id = file_id;
        this.topic_id = topic_id;
        this.filename = filename;
        this.filePath = filePath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public Integer getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(Integer topic_id) {
        this.topic_id = topic_id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
