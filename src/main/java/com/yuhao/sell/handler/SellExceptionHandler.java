package com.yuhao.sell.handler;


import com.yuhao.sell.VO.ResultVO;
import com.yuhao.sell.config.ProjectUrlConfig;
import com.yuhao.sell.exception.AdminAuthorizeException;
import com.yuhao.sell.exception.SellException;
import com.yuhao.sell.exception.SellerAuthorizeException;
import com.yuhao.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * SellExceptionHandler
 *
 * @author CYH
 * @date 2018/3/29
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    //http://yuhao.natapp1.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
        .concat(projectUrlConfig.getWechatOpenAuthorize())
        .concat("/sell/seller/onSellerLogin"));
    }

    @ExceptionHandler(value = AdminAuthorizeException.class)
    public ModelAndView handlerAdminAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/admin/onAdminLogin"));
    }


    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.fail(e.getCode(),e.getMessage());
    }



}
