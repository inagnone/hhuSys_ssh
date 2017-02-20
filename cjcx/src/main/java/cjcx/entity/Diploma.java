package cjcx.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by Administrator on 2017/2/20.
 */
@Entity
public class Diploma {
    private int id;
    private String name;
    private String idNumber;
    private String area;
    private String company;
    private String job;
    private Date approvetime;
    private Date starttime;
    private Date finishtime;
    private String studyhour;
    private String diplomaNumber;
    private Date issuetime;
    private String validity;
    private Date endtime;
    private String issuecompany;
    private String imgpath;
    private String commissionunit;
    private String undertaker;
    private Date firdelay;
    private Date secdelay;
    private String detail;
    private String trainhour;
    private Date examtime;
    private String trainway;
    private String trainer;
    private DiplomaType diplomaType;


    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "idNumber", nullable = false, length = 255)
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "area", nullable = true, length = 255)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "company", nullable = false, length = 255)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "job", nullable = false, length = 255)
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Basic
    @Column(name = "approvetime", nullable = true)
    public Date getApprovetime() {
        return approvetime;
    }

    public void setApprovetime(Date approvetime) {
        this.approvetime = approvetime;
    }

    @Basic
    @Column(name = "starttime", nullable = true)
    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    @Basic
    @Column(name = "finishtime", nullable = true)
    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    @Basic
    @Column(name = "studyhour", nullable = true, length = 255)
    public String getStudyhour() {
        return studyhour;
    }

    public void setStudyhour(String studyhour) {
        this.studyhour = studyhour;
    }

    @Basic
    @Column(name = "diplomaNumber", nullable = false, length = 255)
    public String getDiplomaNumber() {
        return diplomaNumber;
    }

    public void setDiplomaNumber(String diplomaNumber) {
        this.diplomaNumber = diplomaNumber;
    }

    @Basic
    @Column(name = "issuetime", nullable = false)
    public Date getIssuetime() {
        return issuetime;
    }

    public void setIssuetime(Date issuetime) {
        this.issuetime = issuetime;
    }

    @Basic
    @Column(name = "validity", nullable = true, length = 255)
    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    @Basic
    @Column(name = "endtime", nullable = false)
    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    @Basic
    @Column(name = "issuecompany", nullable = true, length = 255)
    public String getIssuecompany() {
        return issuecompany;
    }

    public void setIssuecompany(String issuecompany) {
        this.issuecompany = issuecompany;
    }

    @Basic
    @Column(name = "imgpath", nullable = true, length = 255)
    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    @Basic
    @Column(name = "commissionunit", nullable = true, length = 255)
    public String getCommissionunit() {
        return commissionunit;
    }

    public void setCommissionunit(String commissionunit) {
        this.commissionunit = commissionunit;
    }

    @Basic
    @Column(name = "undertaker", nullable = true, length = 255)
    public String getUndertaker() {
        return undertaker;
    }

    public void setUndertaker(String undertaker) {
        this.undertaker = undertaker;
    }

    @Basic
    @Column(name = "firdelay", nullable = true)
    public Date getFirdelay() {
        return firdelay;
    }

    public void setFirdelay(Date firdelay) {
        this.firdelay = firdelay;
    }

    @Basic
    @Column(name = "secdelay", nullable = true)
    public Date getSecdelay() {
        return secdelay;
    }

    public void setSecdelay(Date secdelay) {
        this.secdelay = secdelay;
    }

    @Basic
    @Column(name = "detail", nullable = true, length = 65535)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "trainhour", nullable = true, length = 255)
    public String getTrainhour() {
        return trainhour;
    }

    public void setTrainhour(String trainhour) {
        this.trainhour = trainhour;
    }

    @Basic
    @Column(name = "examtime", nullable = true)
    public Date getExamtime() {
        return examtime;
    }

    public void setExamtime(Date examtime) {
        this.examtime = examtime;
    }

    @Basic
    @Column(name = "trainway", nullable = true, length = 255)
    public String getTrainway() {
        return trainway;
    }

    public void setTrainway(String trainway) {
        this.trainway = trainway;
    }

    @Basic
    @Column(name = "trainer", nullable = true, length = 255)
    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public DiplomaType getDiplomaType() {
        return diplomaType;
    }

    public void setDiplomaType(DiplomaType diplomaType) {
        this.diplomaType = diplomaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Diploma diploma = (Diploma) o;

        if (id != diploma.id) return false;
        if (name != null ? !name.equals(diploma.name) : diploma.name != null) return false;
        if (idNumber != null ? !idNumber.equals(diploma.idNumber) : diploma.idNumber != null) return false;
        if (area != null ? !area.equals(diploma.area) : diploma.area != null) return false;
        if (company != null ? !company.equals(diploma.company) : diploma.company != null) return false;
        if (job != null ? !job.equals(diploma.job) : diploma.job != null) return false;
        if (approvetime != null ? !approvetime.equals(diploma.approvetime) : diploma.approvetime != null) return false;
        if (starttime != null ? !starttime.equals(diploma.starttime) : diploma.starttime != null) return false;
        if (finishtime != null ? !finishtime.equals(diploma.finishtime) : diploma.finishtime != null) return false;
        if (studyhour != null ? !studyhour.equals(diploma.studyhour) : diploma.studyhour != null) return false;
        if (diplomaNumber != null ? !diplomaNumber.equals(diploma.diplomaNumber) : diploma.diplomaNumber != null)
            return false;
        if (issuetime != null ? !issuetime.equals(diploma.issuetime) : diploma.issuetime != null) return false;
        if (validity != null ? !validity.equals(diploma.validity) : diploma.validity != null) return false;
        if (endtime != null ? !endtime.equals(diploma.endtime) : diploma.endtime != null) return false;
        if (issuecompany != null ? !issuecompany.equals(diploma.issuecompany) : diploma.issuecompany != null)
            return false;
        if (imgpath != null ? !imgpath.equals(diploma.imgpath) : diploma.imgpath != null) return false;
        if (commissionunit != null ? !commissionunit.equals(diploma.commissionunit) : diploma.commissionunit != null)
            return false;
        if (undertaker != null ? !undertaker.equals(diploma.undertaker) : diploma.undertaker != null) return false;
        if (firdelay != null ? !firdelay.equals(diploma.firdelay) : diploma.firdelay != null) return false;
        if (secdelay != null ? !secdelay.equals(diploma.secdelay) : diploma.secdelay != null) return false;
        if (detail != null ? !detail.equals(diploma.detail) : diploma.detail != null) return false;
        if (trainhour != null ? !trainhour.equals(diploma.trainhour) : diploma.trainhour != null) return false;
        if (examtime != null ? !examtime.equals(diploma.examtime) : diploma.examtime != null) return false;
        if (trainway != null ? !trainway.equals(diploma.trainway) : diploma.trainway != null) return false;
        if (trainer != null ? !trainer.equals(diploma.trainer) : diploma.trainer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idNumber != null ? idNumber.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (approvetime != null ? approvetime.hashCode() : 0);
        result = 31 * result + (starttime != null ? starttime.hashCode() : 0);
        result = 31 * result + (finishtime != null ? finishtime.hashCode() : 0);
        result = 31 * result + (studyhour != null ? studyhour.hashCode() : 0);
        result = 31 * result + (diplomaNumber != null ? diplomaNumber.hashCode() : 0);
        result = 31 * result + (issuetime != null ? issuetime.hashCode() : 0);
        result = 31 * result + (validity != null ? validity.hashCode() : 0);
        result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
        result = 31 * result + (issuecompany != null ? issuecompany.hashCode() : 0);
        result = 31 * result + (imgpath != null ? imgpath.hashCode() : 0);
        result = 31 * result + (commissionunit != null ? commissionunit.hashCode() : 0);
        result = 31 * result + (undertaker != null ? undertaker.hashCode() : 0);
        result = 31 * result + (firdelay != null ? firdelay.hashCode() : 0);
        result = 31 * result + (secdelay != null ? secdelay.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (trainhour != null ? trainhour.hashCode() : 0);
        result = 31 * result + (examtime != null ? examtime.hashCode() : 0);
        result = 31 * result + (trainway != null ? trainway.hashCode() : 0);
        result = 31 * result + (trainer != null ? trainer.hashCode() : 0);
        return result;
    }
}
