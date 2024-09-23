package com.mi.search.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.mi.search.model.entity.Picture;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author mi11
 * @version 1.0
 * @project backend-administration-template
 * @description
 * @ClassName PictureDataSource
 */
@Service
@Slf4j
public class PictureServiceImpl  implements PictureService {
    @Override
    public Page<Picture> listPictureByPage(String searchText, long pageNumber, long pageSize) {
        long first = (pageNumber -1) * pageSize;
        String url = String.format("https://cn.bing.com/images/search?q=%s&first=%s",searchText,first);
        try {
            Document doc= Jsoup.connect(url).get();
        Elements select = doc.select(".iuscp");
        List<Picture> pictures = new ArrayList<>();
        long i = pageSize;
        for (Element element : select) {
            Elements imgDiv = element.select("a.iusc");
            Element imgA = imgDiv.get(0);
            String m = imgA.attr("m");
            String jsonStrM = JSONUtil.toJsonStr(m);
            JSONObject jsonObjectM = JSONUtil.parseObj(jsonStrM);
            String murl = (String) jsonObjectM.get("murl");
            Elements titleDiv = element.select("a.inflnk");
            Element titleElement = titleDiv.get(0);
            String title = titleElement.attr("aria-label");
            Picture picture = new Picture();
            picture.setTitle(title);
            picture.setUrl(murl);
            pictures.add(picture);
            i--;
            if (i == 0) {
                break;
            }
        }
        Page<Picture> picturePage = new Page<>(pageNumber, pageSize);
        picturePage.setRecords(pictures);
        picturePage.setSize(pictures.size());
        log.info("获取图片数据成功 =>"+url);
            return picturePage;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取图片数据失败",e);
        }
        return null;
    }

    @Test
    void test(){
        String url = "http://hx.oneshipping.com/finance/subjectdetail#";
    }
}
