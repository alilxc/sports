package com.example.sports.dto.response;

import java.util.List;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-24 22:20
 **/
public class SysGroupingDetailDTO {

    /**
     * 是否团体比赛
     */
    private boolean isTeam;

    private List<SysProjectSignDTO> groupingMemberDTOList;

    public boolean isTeam() {
        return isTeam;
    }

    public void setTeam(boolean team) {
        isTeam = team;
    }

    public List<SysProjectSignDTO> getGroupingMemberDTOList() {
        return groupingMemberDTOList;
    }

    public void setGroupingMemberDTOList(List<SysProjectSignDTO> groupingMemberDTOList) {
        this.groupingMemberDTOList = groupingMemberDTOList;
    }
}
