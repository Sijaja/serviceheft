package dev.sijaja.serviceheft.dto;

public class ToBeReplacedDto {
    private String checkName;
    private String condition;

    public ToBeReplacedDto(String checkName, String condition) {
        this.checkName = checkName;
        this.condition = condition;
    }

    public String getCheckName() { return checkName; }
    public String getCondition() { return condition; }
}
