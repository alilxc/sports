package com.example.sports.service;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.response.CompetitionResultDTO;
import com.example.sports.dto.response.RoleRes;
import com.example.sports.dto.response.ScoreSerchRes;
import com.github.pagehelper.PageInfo;


/**
 * @author xingchao.lxc
 */
public interface SearchService {


    CompetitionResultDTO searchCompetitionStatus(PageRequestBean requestBean, String gameName, int status);

}
