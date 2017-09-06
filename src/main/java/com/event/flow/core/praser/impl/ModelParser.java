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

import com.event.flow.core.EventFlowException;
import com.event.flow.core.ServiceContext;
import com.event.flow.core.helper.XmlHelper;
import com.event.flow.core.model.NodeModel;
import com.event.flow.core.model.ProcessModel;
import com.event.flow.core.model.TransitionModel;
import com.event.flow.core.praser.NodeParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des: xml 转 bean
 */
public class ModelParser {
	/**
	 * 解析流程定义文件，并将解析后的对象放入模型容器中
	 * @param bytes
	 */
	public static ProcessModel parse(byte[] bytes) {
		DocumentBuilder documentBuilder = XmlHelper.createDocumentBuilder();
		if(documentBuilder != null) {
			Document doc = null;
			try {
				doc = documentBuilder.parse(new ByteArrayInputStream(bytes));
				Element processE = doc.getDocumentElement();
				ProcessModel process = new ProcessModel();
				process.setName(processE.getAttribute(NodeParser.ATTR_NAME));
				process.setDisplayName(processE.getAttribute(NodeParser.ATTR_DISPLAYNAME));
				
				NodeList nodeList = processE.getChildNodes();
				int nodeSize = nodeList.getLength();
				for(int i = 0; i < nodeSize; i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						NodeModel model = parseModel(node);
						process.getNodes().add(model);
					}
				}
				
				//循环节点模型，构造变迁输入、输出的source、target
				for(NodeModel node : process.getNodes()) {
					for(TransitionModel transition : node.getOutputs()) {
						String to = transition.getTo();
						for(NodeModel node2 : process.getNodes()) {
							if(to.equalsIgnoreCase(node2.getName())) {
								node2.getInputs().add(transition);
								transition.setTarget(node2);
							}
						}
					}
				}
				return process;
			} catch (SAXException e) {
				e.printStackTrace();
				throw new EventFlowException(e);
			} catch (IOException e) {
				throw new EventFlowException(e);
			}
		} else {
			throw new EventFlowException("documentBuilder is null");
		}
	}
	
	/**
	 * 对流程定义xml的节点，根据其节点对应的解析器解析节点内容
	 * @param node
	 * @return
	 */
	private static NodeModel parseModel(Node node) {
		String nodeName = node.getNodeName();
		Element element = (Element)node;
		NodeParser nodeParser = null;
		try {
			nodeParser = ServiceContext.getContext().findByName(nodeName, NodeParser.class);
			nodeParser.parse(element);
			return nodeParser.getModel();
		} catch (RuntimeException e) {
			throw new EventFlowException(e);
		}
	}
}
