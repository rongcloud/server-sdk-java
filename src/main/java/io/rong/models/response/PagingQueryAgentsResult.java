package io.rong.models.response;


import io.rong.models.agent.AgentModel;
import io.rong.util.GsonUtil;

import java.util.List;


/**
 * @author RongCloud
 */
public class PagingQueryAgentsResult extends ResponseResult {
    private Integer total;
    private Integer totalPages;
    private Boolean hasNext;
    private List<AgentModel> agents;

    public PagingQueryAgentsResult(Integer code, String msg) {
        super(code, msg);
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<AgentModel> getAgents() {
        return agents;
    }

    public void setAgents(List<AgentModel> agents) {
        this.agents = agents;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, PagingQueryAgentsResult.class);
    }
}
