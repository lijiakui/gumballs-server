package com.jack.gumballs.controller;

import com.jack.gumballs.domain.model.Guides;
import com.jack.gumballs.service.GuidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiJiakui on 17/6/13.
 */
@RestController
@RequestMapping("/guides")
public class GuidesController {
    @Autowired
    private GuidesService guidesService;

    @RequestMapping("/selectList")
    public List<Map<String,Object>> selectList(){

        List<Guides> list = guidesService.selectByExample(null);
        List<Map<String,Object>> returnList = new ArrayList<>();
        for(Guides guides : list){
            if(guides.getPid()==0){
                Map<String,Object> map = new HashMap<>();
                map.put("id",guides.getId());
                map.put("name",guides.getName());
                map.put("open",false);
                List childrenList = new ArrayList<>();
                for(Guides children : list){
                    if(children.getPid()==guides.getId()){
                        Map<String,Object> childrenMap = new HashMap<>();
                        childrenMap.put("name",children.getName());
                        childrenMap.put("id",children.getId());
                        childrenList.add(childrenMap);
                    }
                }
                map.put("children",childrenList);
                returnList.add(map);
            }
        }
        return returnList;
    }

    @RequestMapping("/selectById")
    public Guides selectById(Integer id){
        return guidesService.selectByPrimaryKey(id);
    }
}
