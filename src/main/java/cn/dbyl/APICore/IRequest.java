package main.java.cn.dbyl.APICore;

import org.apache.http.HttpEntity;

public interface IRequest {
	public HttpEntity get();
	public HttpEntity post();
	public HttpEntity put();
	public HttpEntity delete();

}
