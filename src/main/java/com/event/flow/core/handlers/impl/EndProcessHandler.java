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
package com.event.flow.core.handlers.impl;


import com.event.flow.core.EventFlowEngine;
import com.event.flow.core.Execution;
import com.event.flow.core.entity.Order;
import com.event.flow.core.handlers.IHandler;

/**
 * 流程技术触发
 */
public class EndProcessHandler implements IHandler {
	/**
	 * 结束当前流程实例，如果存在父流程，则触发父流程继续执行
	 */
	public void handle(Execution execution) {
		EventFlowEngine engine = execution.getEngine();
		Order order = execution.getOrder();

	}
}
