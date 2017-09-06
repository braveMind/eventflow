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
package com.event.flow.core.praser.impl;


import com.event.flow.core.model.DecisionModel;
import com.event.flow.core.model.NodeModel;
import com.event.flow.core.praser.AbstractNodeParser;
import org.w3c.dom.Element;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des: 决策解析
 */
public class DecisionParser extends AbstractNodeParser {
	/**
	 * 产生DecisionModel模型对象
	 */
	protected NodeModel newModel() {
		return new DecisionModel();
	}

	/**
	 * 解析decisition节点的特有属性expr
	 */
	protected void parseNode(NodeModel node, Element element) {
		DecisionModel decision = (DecisionModel)node;
		decision.setExpr(element.getAttribute(ATTR_EXPR));
		decision.setHandleClass(element.getAttribute(ATTR_HANDLECLASS));
	}
}
