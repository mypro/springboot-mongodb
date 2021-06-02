package org.xinhua.gk.repository.condition;

import io.swagger.annotations.ApiModelProperty;

public class ProposalInfoCondition extends BaseCondition{

    @ApiModelProperty("活动 actId")
    private String actId;
    @ApiModelProperty("参与人姓名")
    private String name;

    @ApiModelProperty(value = "排序方式", example = "ASC/DESC")
    private String sortProposalAt;
    @ApiModelProperty("排序字段")
    public static final String SORT_PROPOSAL_DATE = "proposalAt";

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortProposalAt() {
        return sortProposalAt;
    }

    public void setSortProposalAt(String sortProposalAt) {
        this.sortProposalAt = sortProposalAt;
    }
}
