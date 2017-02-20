package cjcx.entity;

/**
 * Created by Administrator on 2017/2/19.
 */
public class DiplomaForm extends Diploma {
    private String diplomaTypeNumber;

    public String getDiplomaTypeNumber() {
        return diplomaTypeNumber;
    }

    public void setDiplomaTypeNumber(String diplomaTypeNumber) {
        this.diplomaTypeNumber = diplomaTypeNumber;
    }

    public Diploma createDiploma(){
        Diploma d = new Diploma();
        d.setName(super.getName());
        d.setApprovetime(super.getApprovetime());
        d.setArea(super.getArea());
        d.setCommissionunit(super.getCommissionunit());
        d.setCompany(super.getCompany());
        d.setDetail(super.getDetail());
        d.setDiplomaNumber(super.getDiplomaNumber());
        d.setDiplomaType(super.getDiplomaType());
        d.setEndtime(super.getEndtime());
        d.setExamtime(super.getExamtime());
        d.setFinishtime(super.getFinishtime());
        d.setIdNumber(super.getIdNumber());
        d.setImgpath(super.getImgpath());
        d.setIssuecompany(super.getIssuecompany());
        d.setIssuetime(super.getIssuetime());
        d.setJob(super.getJob());
        d.setSecdelay(super.getSecdelay());
        d.setStarttime(super.getStarttime());
        d.setStudyhour(super.getStudyhour());
        d.setTrainer(super.getTrainer());
        d.setTrainhour(super.getTrainhour());
        d.setTrainway(super.getTrainway());
        d.setUndertaker(super.getUndertaker());
        d.setValidity(super.getValidity());
        return d;
    }
}
