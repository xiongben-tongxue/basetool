package com.gws;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
/**
 * @author:wangdong
 * @description:已经测试通过，这个是OK的
 */
public class sample {

    public static void main(String[] args) {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI8VNtGyEjfGCS", "sJ2idqTOL7Cz1leqNmd0CWokCzRVlv");

        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setAccountName("usdg@mail.timesea.cn");
            request.setFromAlias("usdg");
            request.setAddressType(1);
            request.setTagName("usdg");
            request.setReplyToAddress(true);
            request.setToAddress("708934989@qq.com");
            request.setSubject("测试邮件");
            request.setHtmlBody("欢迎您注册");
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
