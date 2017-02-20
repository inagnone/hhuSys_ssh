package cjcx.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/2/7.
 */
@Entity
public class Grade {
    private int id;
    private Cj cj;
    private Test test;
    private Float socre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cj getCj() {
        return cj;
    }

    public void setCj(Cj cj) {
        this.cj = cj;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Basic
    @Column(name = "socre", nullable = true, precision = 0)
    public Float getSocre() {
        return socre;
    }

    public void setSocre(Float socre) {
        this.socre = socre;
    }

}
