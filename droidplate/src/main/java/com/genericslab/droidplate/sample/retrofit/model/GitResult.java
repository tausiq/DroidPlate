package com.genericslab.droidplate.sample.retrofit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shahab on 1/24/16.
 */
public class GitResult {

    private Integer totalCount;
    private Boolean incompleteResults;
    private List<Item> items = new ArrayList<Item>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The totalCount
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     *
     * @param totalCount
     * The total_count
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     *
     * @return
     * The incompleteResults
     */
    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    /**
     *
     * @param incompleteResults
     * The incomplete_results
     */
    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    /**
     *
     * @return
     * The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "GitResult{" +
                "totalCount=" + totalCount +
                ", incompleteResults=" + incompleteResults +
                ", items=" + items +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
