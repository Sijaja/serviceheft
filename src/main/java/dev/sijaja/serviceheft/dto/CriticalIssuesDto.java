package dev.sijaja.serviceheft.dto;

public class CriticalIssuesDto {
    private String source;
    private String condition;

    public CriticalIssuesDto(String condition, String source) {
        this.condition = condition;
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}