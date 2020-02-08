package com.spboot.community.provider;

import com.alibaba.fastjson.JSONObject;
import com.spboot.community.dto.GitHubProviderDTO;
import com.spboot.community.utils.http.HttpUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {

    public String getAccessToken(GitHubProviderDTO gitHubProviderDTO){
        String url = "https://github.com/login/oauth/access_token";
        String jsonParam = JSONObject.toJSONString(gitHubProviderDTO);
        try {

            String result = HttpUtils.sendOkHttpPost(url, jsonParam);
            return result.substring(result.indexOf("=")+1, result.indexOf("&"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String GetGitHubUser(String accessToken){
        String url = "https://api.github.com/user?access_token=" + accessToken;
        try {
            return HttpUtils.sendOkHttpGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
