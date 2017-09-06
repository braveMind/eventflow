/* Copyright 2013-2015 www.snakerflow.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.event.flow.core.praser;


import com.event.flow.core.helper.XmlHelper;
import com.event.flow.core.model.NodeModel;
import com.event.flow.core.model.TransitionModel;
import org.w3c.dom.Element;

import java.util.List;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des: dom解析
 */
public abstract class AbstractNodeParser implements NodeParser {
	/**
	 * 模型对象
	 */
	protected NodeModel model;
	
	/**
	 * 实现NodeParser接口的parse函数
	 * 由子类产生各自的模型对象，设置常用的名称属性，并且解析子节点transition，构造TransitionModel模型对象
	 */
	public void parse(Element element) {
		model = newModel();
		model.setName(element.getAttribute(ATTR_NAME));
		model.setDisplayName(element.getAttribute(ATTR_DISPLAYNAME));

		
		List<Element> transitions = XmlHelper.elements(element, NODE_TRANSITION);
		for(Element te : transitions) {
			TransitionModel transition = new TransitionModel();
			transition.setName(te.getAttribute(ATTR_NAME));
			transition.setDisplayName(te.getAttribute(ATTR_DISPLAYNAME));
			transition.setTo(te.getAttribute(ATTR_TO));
			transition.setExpr(te.getAttribute(ATTR_EXPR));

			transition.setSource(model);
			model.getOutputs().add(transition);
		}
		
		parseNode(model, element);
	}
	
	/**
	 * 子类可覆盖此方法，完成特定的解析
	 * @param model
	 * @param element
	 */
	protected void parseNode(NodeModel model, Element element) {
		
	}
	
	/**
	 * 抽象方法，由子类产生各自的模型对象
	 * @return
	 */
	protected abstract NodeModel newModel();

	/**
	 * 返回模型对象
	 */
	public NodeModel getModel() {
		return model;
	}
}
