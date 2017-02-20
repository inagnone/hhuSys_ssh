package cjcx.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/4.
 */
@Entity
public class Exam {
    private String number;
    private String name;
    private Set<Test> tests;
    private String listTests;

    @Id
    @Column(name = "number", nullable = false, length = 255)
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

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

    public String getListTests() {
        return listTests;
    }

    public void setListTests(String s){
    }

    public void setListTests() {
        StringBuilder builder = new StringBuilder();
        if(tests.isEmpty()){
            builder.append("");
        }else{
            for(Test t:tests){
                builder.append(t.getName());
                builder.append(",");
            }
            builder.deleteCharAt(builder.length()-1);
        }
        this.listTests =  builder.toString();
    }
}
