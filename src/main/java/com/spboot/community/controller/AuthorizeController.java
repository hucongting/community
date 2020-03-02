package com.spboot.community.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spboot.community.dto.GitHubProviderDTO;
import com.spboot.community.mapper.UserMapper;
import com.spboot.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping("github")
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUrl;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("login/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletRequest request){
        GitHubProviderDTO gitHubProviderDTO = new GitHubProviderDTO();
        gitHubProviderDTO.setClient_id(clientId);
        gitHubProviderDTO.setClient_secret(clientSecret);
        gitHubProviderDTO.setCode(code);
        gitHubProviderDTO.setRedirect_uri(redirectUrl);
        gitHubProviderDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(gitHubProviderDTO);
        String gitHubUser = gitHubProvider.GetGitHubUser(accessToken);
        if(gitHubUser != null) {
            JSONObject userJson = JSON.parseObject(gitHubUser);
            request.getSession().setAttribute("user", userJson);
            userMapper.insert(userJson.getString("id"),userJson.getString("name"), UUID.randomUUID().toString().replaceAll("\\-", ""));
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }

}
