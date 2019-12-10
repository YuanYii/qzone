package top.yuanyii.sms;

import jdk.net.SocketFlow;
import org.json.JSONException;
import top.yuanyii.entity.Result;
import top.yuanyii.entity.StatusCode;
import top.yuanyii.sms.httpclient.HTTPException;

import java.io.IOException;

/**
 * @Author 闫佳兴
 * @CreateDate 2019/12/9 21:23
 * @Description
 */
public class SendSms {
    /**
     * 短信发送方法
     * @param code 具体的验证码，4-6位纯数字
     * @param phoneNumber 要发送的手机号(标准的11位手机号，不用手机号前+86)
     * @return
     */
    public static Result send(String code, String phoneNumber){
        // 短信应用SDK AppID
        int appid = 1400292122;
        // 短信应用SDK AppKey
        String appkey = "a315c820d5443afe26a7e0720ba554ee";
        // 需要发送短信的手机号码
        String[] phoneNumbers = {phoneNumber};
        // 真实的模板ID需要在短信控制台中申请
        int templateId = 490008;
        // 签名参数使用的是`签名内容`，而不是`签名ID`
        String smsSign = "ronins";
        // 指定模板ID单发短信
        try {
            String codes = code;
            String[] params = {codes};
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");
            System.out.println(result);
            return new Result(true, StatusCode.OK,"发送成功");
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"发送失败");
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"发送失败");
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"发送失败");
        }
    }
}
