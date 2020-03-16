package com.laputa.utils;

import com.laputa.enums.ResultEnum;
import com.laputa.vo.ResultVO;

public class ResultUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultEnum.SUCCESS.getMsg());
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO failure() {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(null);
        resultVO.setCode(ResultEnum.FAILURE.getCode());
        resultVO.setMsg(ResultEnum.FAILURE.getMsg());
        return resultVO;
    }
}
