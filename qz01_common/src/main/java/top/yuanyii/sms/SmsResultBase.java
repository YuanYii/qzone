package top.yuanyii.sms;

import org.json.JSONException;
import org.json.JSONObject;
import top.yuanyii.sms.httpclient.HTTPResponse;

/**
 * @Author 闫佳兴
 * @CreateDate 2019/12/9 19:43
 * @Description
 */
public abstract class SmsResultBase {
    protected HTTPResponse response;

    /**
     * Parse result from HTTPResponse
     *
     * @param response  HTTP response from api return
     * @return SmsResultbase
     * @throws JSONException  json parse exception
     */
    public abstract SmsResultBase parseFromHTTPResponse(HTTPResponse response)
            throws JSONException;

    /**
     * Parse HTTP response to JSONObject
     *
     * @param response  HTTP response
     * @return JSONObject
     * @throws JSONException  json parse exception
     */
    public JSONObject parseToJson(HTTPResponse response) throws JSONException {
        // Set raw response
        this.response = response;
        // May throw JSONException
        return new JSONObject(response.body);
    }

    /**
     * Get raw response
     *
     * @return HTTPResponse
     */
    public HTTPResponse getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return response.body;
    }

}
