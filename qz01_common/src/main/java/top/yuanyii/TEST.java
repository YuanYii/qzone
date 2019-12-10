package top.yuanyii;

import cn.hutool.core.util.RandomUtil;
import top.yuanyii.entity.Result;
import top.yuanyii.sms.SendSms;

/**
 * @Author 闫佳兴
 * @CreateDate 2019/12/9 21:32
 * @Description
 */
public class TEST {
    public static void main(String[] args) {
        String code = RandomUtil.randomNumbers(6);
        System.out.println(code);
        /**
         * 任可   15229059863
         * 闫佳兴 15686903006
         */
        String phone = "15686903006";
        Result send = SendSms.send(code, phone);
        System.out.println(send.toString());
    }
}
