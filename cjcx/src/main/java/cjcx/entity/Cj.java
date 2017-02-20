package cjcx.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/4.
 */
@Entity
public class Cj {
    private String idNumber;
    private String studentName;
    private Date examDate;
    private String batchNumber;
    private String company;
    private int id;
    private Set<Grade> grades;
    private Exam exam;

    public void resetValue(Cj cj){
        idNumber = cj.getIdNumber();
        studentName = cj.getStudentName();
        examDate = cj.getExamDate();
        batchNumber = cj.getBatchNumber();
        company = cj.getCompany();
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    @Basic
    @Column(name = "id_number", nullable = false, length = 255)
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "student_name", nullable = false, length = 255)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "exam_date", nullable = false)
    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    @Basic
    @Column(name = "batch_number", nullable = false, length = 255)
    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Basic
    @Column(name = "company", nullable = true, length = 255)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cj cj = (Cj) o;

        if (id != cj.id) return false;
        if (idNumber != null ? !idNumber.equals(cj.idNumber) : cj.idNumber != null) return false;
        if (studentName != null ? !studentName.equals(cj.studentName) : cj.studentName != null) return false;
        if (examDate != null ? !examDate.equals(cj.examDate) : cj.examDate != null) return false;
        if (batchNumber != null ? !batchNumber.equals(cj.batchNumber) : cj.batchNumber != null) return false;
        if (company != null ? !company.equals(cj.company) : cj.company != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNumber != null ? idNumber.hashCode() : 0;
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + (examDate != null ? examDate.hashCode() : 0);
        result = 31 * result + (batchNumber != null ? batchNumber.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
