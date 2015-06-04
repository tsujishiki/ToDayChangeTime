package org.soya.mcore.controller;

import org.soya.mcore.constant.Status;
import org.soya.mcore.dto.ReturnBody;
import org.soya.mcore.model.Dictionary;
import org.soya.mcore.service.BaseDataSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 基础数据通用处理controller
 * Created by FunkySoya on 2015/5/31.
 */

@RestController
public class BaseDataController extends BaseController {

    @Autowired
    BaseDataSer baseDataSer;

    @RequestMapping(value = {"/baseData/{type}"},method = RequestMethod.GET)
    @ResponseBody
    public ReturnBody getByType(@PathVariable("type") String type) throws Exception {

        List<Dictionary> dictList = baseDataSer.getListByType(type);
        ReturnBody returnBody = new ReturnBody();
        returnBody.setStatus(Status.SUCCESS);
        returnBody.setData(dictList);

        return  returnBody;
    }

}
