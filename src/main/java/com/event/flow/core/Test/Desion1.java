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
package com.event.flow.core.Test;


import com.event.flow.core.Execution;
import com.event.flow.core.handlers.DecisionHandler;


public class Desion1 implements DecisionHandler {
	
	public String decide(Execution execution) {
		System.out.println("ss");
		/*Map map=execution.getArgs();
		map.put("content","11");*/
		execution.getArgs().put("content", 200);
		return "";
	}

}
