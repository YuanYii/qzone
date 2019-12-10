package top.yuanyii.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 任可
 * 2019/12/10  10:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    //    标记
    private boolean flag;
    //    状态码
    private Integer code;
    //    信息
    private String message;
    //    对象
    private Object data;

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

}
