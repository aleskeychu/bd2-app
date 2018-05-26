package ru.ifmo.basecourse.dto.cassandra;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table
public class RuleLog {

    @JsonCreator
    public RuleLog(Long ruleId, String name, Date when, String validatee, UUID id, Boolean isComposite, Boolean result) {
        this.ruleId = ruleId;
        this.name = name;
        this.when = when;
        this.validatee = validatee;
        this.id = id;
        this.isComposite = isComposite;
        this.result = result;
    }

    @PrimaryKey
    private RuleLogKey pk;
    @Column
    private Long ruleId;
    @Column
    private String name;
    @Column
    private Date when;
    @Column
    private String validatee;
    @Column
    private UUID id;
    @Column
    private Boolean isComposite;
    @Column
    private Boolean result;

    public RuleLogKey getPk() {
        return pk;
    }

    public void setPk(RuleLogKey pk) {
        this.pk = pk;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getwhen() {
        return when;
    }

    public void setwhen(Date when) {
        this.when = when;
    }

    public String getValidatee() {
        return validatee;
    }

    public void setValidatee(String validatee) {
        this.validatee = validatee;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getComposite() {
        return isComposite;
    }

    public void setComposite(Boolean composite) {
        isComposite = composite;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

}
