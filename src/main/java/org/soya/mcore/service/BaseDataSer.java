package org.soya.mcore.service;

import org.soya.mcore.model.Dictionary;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by FunkySoya on 2015/5/31.
 */

public interface BaseDataSer {

    List<Dictionary> getByCode(String code);
}
