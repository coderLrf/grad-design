package com.example.designtopicselectionsystem.domain;

// 留言表
public class ChatRecordAdmin {

    private Integer id;
    private Integer teacher_id;
    private Integer student_id;
    private String content; // 留言内容
    private Long create_time; // 创建时间
    private Integer message_side; // 留言方id
    private int flag; // 状态：0表示被屏蔽了
    private Teacher teacher; // 教师对象
    private Student student; // 学生对象
    private String recordName; // 留言方姓名
    private String receiverName; // 接收方姓名

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Integer getMessage_side() {
        return message_side;
    }

    public void setMessage_side(Integer message_side) {
        this.message_side = message_side;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Override
    public String toString() {
        return "ChatRecord{" +
                "id=" + id +
                ", teacher_id=" + teacher_id +
                ", student_id=" + student_id +
                ", content='" + content + '\'' +
                ", create_time=" + create_time +
                ", message_side=" + message_side +
                ", flag=" + flag +
                ", teacher=" + teacher +
                ", student=" + student +
                '}';
    }
}
