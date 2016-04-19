package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.JsonviewProcessor;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * jsonview
 * 
 * @author qiuzhenhao
 *
 */
public class Jsonview extends ObjectElement {

	private String namespace;
	private String id;
	private Extend extend;

	public Jsonview(String namespace, String id) {
		this.namespace = namespace;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getNamespace() {
		return namespace;
	}

	public Optional<Extend> getExtend() {
		return Optional.ofNullable(extend);
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		JsonviewProcessor jsonviewElementProcessor = new JsonviewProcessor(context, this, parentNode, parentExpression, data);
		return Optional.of(jsonviewElementProcessor);
	}

	/**
	 * Extend information encapsulation entities
	 * 
	 * @author qiuzhenhao
	 * @since 3.1.0
	 */
	public class Extend {

		private String namesapce;
		private String jsonviewId;
		private String port;

		public Extend(String extendStr, String defaultNamespace) {
			String front = StringUtils.substringBefore(extendStr, ":");
			this.port = StringUtils.substringAfter(extendStr, ":");
			if (front.contains(".")) {
				this.namesapce = StringUtils.substringBefore(front, ".");
				this.jsonviewId = StringUtils.substringAfter(front, ".");
			} else {
				this.namesapce = defaultNamespace;
				this.jsonviewId = front;
			}
		}

		public String getNamesapce() {
			return namesapce;
		}

		public String getJsonviewId() {
			return jsonviewId;
		}

		public String getPort() {
			return port;
		}

	}

}
