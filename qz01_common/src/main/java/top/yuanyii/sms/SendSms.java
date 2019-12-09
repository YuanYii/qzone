package top.yuanyii.sms;

import org.json.JSONException;
import top.yuanyii.commons.ResultObj;
import top.yuanyii.sms.httpclient.HTTPException;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Author 闫佳兴
 * @CreateDate 2019/12/9 21:23
 * @Description
 */
public class SendSms {
    public static ResultObj send(String code, String[] phoneNumber){
        // 短信应用SDK AppID
        int appid = 1400292122;

        // 短信应用SDK AppKey
        String appkey = "a315c820d5443afe26a7e0720ba554ee";

        // 需要发送短信的手机号码
        String[] phoneNumbers = phoneNumber;

        // 短信模板ID，需要在短信应用中申请
        // NOTE: 这里的模板ID`7839`只是一个示例，
        // 真实的模板ID需要在短信控制台中申请
        int templateId = 490008;

        // 签名
        // 签名参数使用的是`签名内容`，而不是`签名ID`
        String smsSign = "ronins";
        // 指定模板ID单发短信
        try {
            String codes = code;
            String[] params = {codes};
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print(result);
            return new ResultObj().SEND_SUCCESS;
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return new ResultObj().SEND_ERROR;
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            return new ResultObj().SEND_ERROR;
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return new ResultObj().SEND_ERROR;
        }
    }
}
