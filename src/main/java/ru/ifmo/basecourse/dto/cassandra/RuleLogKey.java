package ru.ifmo.basecourse.dto.cassandra;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@PrimaryKeyClass
public class RuleLogKey implements Serializable {

    @PrimaryKeyColumn(ordinal=0, type=PrimaryKeyType.PARTITIONED)
    private Long ruleId;
    @PrimaryKeyColumn(ordinal=1, type=PrimaryKeyType.CLUSTERED)
    private String name;
    @PrimaryKeyColumn(ordinal=2, type=PrimaryKeyType.CLUSTERED)
    private Date timestamp;
    @PrimaryKeyColumn(ordinal=3, type=PrimaryKeyType.CLUSTERED)
    private String validatee;
    @PrimaryKeyColumn(ordinal=4, type=PrimaryKeyType.CLUSTERED)
    private UUID id;
    @PrimaryKeyColumn(ordinal=5, type=PrimaryKeyType.CLUSTERED)
    private Boolean isComposite;
    @PrimaryKeyColumn(ordinal=6, type=PrimaryKeyType.CLUSTERED)
    private Boolean result;

    public Long getRuleId() {
        return ruleId;
    }

    public String getName() {
        return name;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getValidatee() {
        return validatee;
    }

    public UUID getId() {
        return id;
    }

    public Boolean getComposite() {
        return isComposite;
    }

    public Boolean getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleLogKey that = (RuleLogKey) o;
        return Objects.equals(getRuleId(), that.getRuleId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getTimestamp(), that.getTimestamp()) &&
                Objects.equals(getValidatee(), that.getValidatee()) &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(isComposite, that.isComposite) &&
                Objects.equals(getResult(), that.getResult());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getRuleId(), getName(), getTimestamp(), getValidatee(), getId(), isComposite, getResult());
    }
}
