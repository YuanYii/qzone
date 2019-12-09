package top.yuanyii;

import cn.hutool.core.util.RandomUtil;
import top.yuanyii.commons.ResultObj;
import top.yuanyii.sms.SendSms;

/**
 * @Author 闫佳兴
 * @CreateDate 2019/12/9 21:32
 * @Description
 */
public class TEST {
    public static void main(String[] args) {
        String code = RandomUtil.randomNumbers(6);
        String p = "15686903006";
        String[] phone = {p};
        ResultObj send = SendSms.send(code, phone);
        System.out.println(send.getMsg());
    }
}
