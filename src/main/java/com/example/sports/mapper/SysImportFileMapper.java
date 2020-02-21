package com.example.sports.mapper;

import com.example.sports.model.SysImportFile;
import com.example.sports.model.SysImportFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysImportFileMapper {

    int insert(SysImportFile importFile);

    List<SysImportFile> selectByExample(SysImportFileExample example);

    int updateByExample(@Param("record") SysImportFile importFile,
                        @Param("example") SysImportFileExample example);
}
