package dev.sijaja.serviceheft.dto;

import java.util.List;
import java.util.Map;

public class YearlyMaintenanceCostsDto {
    private List<String> months;
    private List<Double> costs;
    private Map<String, List<Double>> types;

    public YearlyMaintenanceCostsDto(List<String> months, List<Double> costs, Map<String, List<Double>> types) {
        this.months = months;
        this.costs = costs;
        this.types = types;
    }

    public List<String> getMonths() { return months; }
    public List<Double> getCosts() { return costs; }
    public Map<String, List<Double>> getTypes() { return types; }
}