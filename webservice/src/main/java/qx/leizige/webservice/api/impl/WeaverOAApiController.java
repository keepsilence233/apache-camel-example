package qx.leizige.webservice.api.impl;


import org.springframework.stereotype.Component;
import qx.leizige.webservice.api.WeaverOAApi;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @WebService 使接口为webService接口
 * @name 暴露服务名称
 * @targetNamespace 命名空间
 * @endpointInterface 接口全限定类名
 **/
@Component
@WebService(serviceName = "OAWebService", targetNamespace = "http://impl.api.webservice.leizige.qx/",
        endpointInterface = "qx.leizige.webservice.api.WeaverOAApi")
public class WeaverOAApiController implements WeaverOAApi {


    @Override
    public String examinationPassed(@WebParam(name = "requestId") String requestId) {
        System.out.println("WeaverOA 审核通过,requestId为:" + requestId);
        return "success";
    }

    @Override
    public String auditNotPassed(@WebParam(name = "requestId") String requestId) {
        System.out.println("WeaverOA 审核驳回,requestId为:" + requestId);
        return "success";
    }

    @Override
    public String extMethod(String param1, String param2, String param3) {
        return "success";
    }
}
