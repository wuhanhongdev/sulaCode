package com.sula.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.sula.service.LoginService;
import com.sula.service.impl.LoginServiceImpl;
import com.sula.util.ResultJson;
import com.sula.util.Status;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * App首页接口
 */
public class AppIndexController extends Controller {
    private LoginService loginService = new LoginServiceImpl();
    /**
     * App首页
     */
    public void index(){

    }

    /**
     * App登录注册
     */
    public void login(){
        //电话
        String mobile = getPara("mobile");
        //邀请码
        String invitationCode = getPara("invitationCode");
        //短信验证码
        String messageCode = getPara("messageCode");
        ResultJson json = new ResultJson();
        if (StringUtils.isEmpty(mobile)){
            json.setCode(Status.fail);
            json.setMessage("手机号码不能为空");
        }else if(StringUtils.isEmpty(messageCode)){
            json.setCode(Status.fail);
            json.setMessage("短信验证码不能为空");
        }else{
            //校验都通过
            Record record = loginService.login(mobile,messageCode);
            if(record == null){
                json.setCode(Status.fail);
                json.setMessage("手机号码错误或者短信验证码错误");
            }else{
                json.setCode(Status.success);
                json.setMessage("登录成功");
                json.setResult(record);
            }
        }
        //将处理对象返回出去
        renderJson(json);
    }

    /**
     * 短信验证码发送,短信发送后，将发送的code存入用户表中
     */
    public void sendVerifyCode(){
        //电话
        String mobile = getPara("mobile");

        ResultJson json = new ResultJson();
        if (StringUtils.isEmpty(mobile)){
            json.setCode(Status.fail);
            json.setMessage("手机号码不能为空");
        }else{
            String vcode = createRandomVcode();
            //TODO 调用短信接口发送

            //发送成功后将其新增入库
            Record record = new Record();
            record.set("mobile",mobile).set("password",vcode).set("create_time",new Date()).set("balance",0.00);

            int resultNum = loginService.saveLoginUser(record);

            if(Status.success == resultNum){
                json.setCode(Status.success);
                json.setMessage("验证码获取成功");
                json.setResult("{vcode:"+vcode+"}");
            }else{
                json.setCode(Status.fail);
                json.setMessage("验证码获取失败,请重试");
            }
        }

        renderJson(json);
    }

    /**
     * 生成6位数字验证码
     * @return
     */
    public static String createRandomVcode(){
        //验证码
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }
}
