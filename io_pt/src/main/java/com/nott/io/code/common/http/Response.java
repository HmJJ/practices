package com.nott.io.code.common.http;

/**
 * Created by allen on 2016/4/13.
 */
public class Response {
    public int status;
    public String content;

    public Response(int status,String content) {
        this.status = status;
        this.content = content;
    }

    public int getStatus()
    {
        return status;
    }

    public String getContent()
    {
        return content;
    }
}
