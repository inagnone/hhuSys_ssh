package cjcx.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/2/19.
 */
@Entity
public class DiplomaType {
    @Id
    private String number;
    private String name;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
