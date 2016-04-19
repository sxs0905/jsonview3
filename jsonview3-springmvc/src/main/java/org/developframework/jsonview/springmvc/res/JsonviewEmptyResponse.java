package org.developframework.jsonview.springmvc.res;

import org.developframework.jsonview.data.HashDataModel;

/**
 * empty jsonview response
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewEmptyResponse extends JsonviewResponse {

	private static final long serialVersionUID = -8022918938928719425L;

	public JsonviewEmptyResponse(String namespace, String jsonviewId) {
		super(namespace, jsonviewId, new HashDataModel());
	}

}
