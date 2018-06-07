package com.yuhao.sell.utils;

import com.yuhao.sell.VO.ResultVO;

/**
 * ResultVOUtil
 *
 * @author CYH
 * @date 2018/3/29
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setData(object);
        resultVO.setMsg("成功");
        resultVO.setCode(0);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO fail(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setMsg(msg);
        resultVO.setCode(code);
        return resultVO;
    }


}
