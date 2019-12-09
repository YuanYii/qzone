package top.yuanyii.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 闫佳兴
 * @data 2019/11/16 16:51
 * @desc Json数据实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {

    /**
     * 状态码 0 为正常OK
     */
    private Integer code=0;
    /**
     * 返回的消息msg
     */
    private String msg="";
    /**
     * 总数个数
     */
    private Long count=0L;
    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 返回总数和数据
     * @param count 总数
     * @param data JSON字符串数据
     */
    public DataGridView(Long count, Object data) {
        super();
        this.count = count;
        this.data = data;
    }

    /**
     * 返回数据
     * @param data JSON数据
     */
    public DataGridView(Object data) {
        super();
        this.data = data;
    }
}
