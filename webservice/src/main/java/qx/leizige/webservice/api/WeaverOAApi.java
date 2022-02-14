package qx.leizige.webservice.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 * @WebService 使接口为webService接口
 * @name 暴露服务名称
 * @targetNamespace 命名空间
 * @WebMethod 注释信息 标识是webService暴露的方法
 **/
@WebService(name = "weaverOA", targetNamespace = "http://impl.api.webservice.leizige.qx/")
public interface WeaverOAApi {

    /**
     * OA审核通过
     */
    @WebMethod(action = "http://impl.api.webservice.leizige.qx/examinationPassed")
    String examinationPassed(@WebParam(name = "requestId", targetNamespace = "http://impl.api.webservice.leizige.qx/") String requestId);

    /**
     * OA审核驳回
     */
    @WebMethod(action = "http://impl.api.webservice.leizige.qx/auditNotPassed")
    String auditNotPassed(@WebParam(name = "requestId", targetNamespace = "http://impl.api.webservice.leizige.qx/") String requestId);
}

