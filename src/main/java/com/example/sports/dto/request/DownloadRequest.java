package com.example.sports.dto.request;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-09-15 23:41
 **/
public class DownloadRequest {

    private String gameName;

    private String status;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
