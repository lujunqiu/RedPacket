package com.lujunqiu.service;

import com.alibaba.fastjson.JSON;
import com.lujunqiu.pojo.Result;
import com.lujunqiu.pojo.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.util.Date;

/**
 * 使用心知天气接入天气预报的api的服务类
 * Created by qiu on 18-1-28.
 */
@Service
public class WeatherService {

    private String TIANQI_DAILY_WEATHER_URL = "https://api.seniverse.com/v3/weather/daily.json";

    private String TIANQI_API_SECRET_KEY = "kob5xpvz97bbaof4"; //

    private String TIANQI_API_USER_ID = "UD586E9483"; //

    /**
     * Generate HmacSHA1 signature with given data string and key
     * @param data
     * @param key
     * @return
     * @throws SignatureException
     */
    private String generateSignature(String data, String key) throws SignatureException {
        String result;
        try {
            // get an hmac_sha1 key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1");
            // get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
            result = new sun.misc.BASE64Encoder().encode(rawHmac);
        }
        catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    /**
     * Generate the URL to get diary weather
     * @param location
     * @param language
     * @param unit
     * @param start
     * @param days
     * @return
     */
    public String generateGetDiaryWeatherURL(
            String location,
            String language,
            String unit,
            String start,
            String days
    )  throws SignatureException, UnsupportedEncodingException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String params = "ts=" + timestamp + "&ttl=30&uid=" + TIANQI_API_USER_ID;
        String signature = URLEncoder.encode(generateSignature(params, TIANQI_API_SECRET_KEY), "UTF-8");
        return TIANQI_DAILY_WEATHER_URL + "?" + params + "&sig=" + signature + "&location=" + location + "&language=" + language + "&unit=" + unit + "&start=" + start + "&days=" + days;
    }

    /**
     * 通过心知天气api提供的url获取查询天气的json数据
     * @param url
     * @return
     * @throws IOException
     */
    public String getJson(String url) throws IOException {
        HttpURLConnection connection = null;
        URL url1 = new URL(url);
        connection = (HttpURLConnection) url1.openConnection();
        connection.connect();
        int status = connection.getResponseCode();

        StringBuilder json = new StringBuilder();
        if (status == 200) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String temp = null;

            while ((temp = bufferedReader.readLine()) != null) {
                json.append(temp);
            }
        }
        return json.toString();
    }


    /**
     * 外部调用的接口
     * @param location 地点
     * @param days 查询的天数
     * @return
     */
    public Result getWeatherInfo(String location, int days) {
        WeatherService service = new WeatherService();
        Result result = null;
        try {
            String url = service.generateGetDiaryWeatherURL(
                    location,
                    "zh-Hans",
                    "c",
                    "1",
                    days + ""
            );
            result = JSON.parseObject(service.getJson(url), Result.class);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return result;
    }

    public static void main(String args[]){
        WeatherService demo = new WeatherService();
        try {
            String url = demo.generateGetDiaryWeatherURL(
                    "wuhan",
                    "zh-Hans",
                    "c",
                    "1",
                    "1"
            );
            System.out.println("URL:" + url);
            System.out.println(demo.getJson(url));

            Result result = JSON.parseObject(demo.getJson(url), Result.class);

            System.out.println(result.getResults().get(0).getDaily().get(0).getHigh());
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

}
