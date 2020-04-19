package com.spboot.community.test;

import com.spboot.community.utils.http.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaiduMapTest {


    public static void main(String[] args) {

        String ak = "EdDhfyxHFvH1XCyVA9i8MZ3fWwahFUut";
        //经纬度小数点后不超过6位
        //起点
        //嘉禾望岗D出口 23.2438040000,113.2950450000
        String[] origin = {"23.243804","113.295045"};
        //终点
        //白云湖公园北门 23.2395810000,113.2471120000
        String[] destination = {"23.239581","113.247112"};
        //百度地图app上路线  距离最近 6.8km   //26min

        //0：默认
        //2：距离最短（只返回一条路线，路线距离相对稳定）
        //3：不走高速
        //4：高速优先
        //5：躲避拥堵
        //6：少收费
        //7: 躲避拥堵 & 高速优先
        //8: 躲避拥堵 & 不走高速
        //9: 躲避拥堵 & 少收费
        //10: 躲避拥堵 & 不走高速 & 少收费
        //11: 不走高速 & 少收费
        //12: 躲避拥堵 & 距离最短
        String tactics = "2";
        //alternatives 是否返回备选路线
        //0：返回一条推荐路线
        //1：返回1-3条路线供选择
        String alternatives = "0";
        Map<String,String> param = new HashMap<>();
        param.put("ak",ak);
        param.put("origin",origin[0] +","+ origin[1]);
        param.put("destination",destination[0] +","+ destination[1]);
        param.put("tactics",tactics);
        param.put("alternatives",alternatives);

        try {
            String result = HttpUtils.sendOkHttpGet("http://api.map.baidu.com/direction/v2/driving",param);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
