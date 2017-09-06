package com.event.flow.core.cfg;

import com.event.flow.core.Context;
import com.event.flow.core.EventFlowEngine;
import com.event.flow.core.EventFlowException;
import com.event.flow.core.ServiceContext;
import com.event.flow.core.helper.ClassHelper;
import com.event.flow.core.helper.StreamHelper;
import com.event.flow.core.helper.StringHelper;
import com.event.flow.core.helper.XmlHelper;
import com.event.flow.core.impl.SimpleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jun
 * 17/9/3 下午2:50.
 * des: 配置引擎启动信息
 *
 */
public class Configuration {

    private static final String BASE_CONFIG_FILE = "base.config.xml";
    private final static String EXT_CONFIG_FILE = "ext.config.xml";
    private static final Logger log = LoggerFactory.getLogger(Configuration.class);

    /*设置上下文环境*/
    public Configuration(Context context) {
        ServiceContext.setContext(context);
    }

    public Configuration() {
        this(new SimpleContext());
    }

    /**
     * 需要事务管理的class类型
     */
    private Map<String, Class<?>> txClass = new HashMap<String, Class<?>>();

    /**
     * 依赖配置文件注入
     *
     * @return
     * @throws EventFlowException
     */
    public EventFlowEngine buildEventFlowEngine() throws EventFlowException {
        if (log.isInfoEnabled()) {
            log.info("EventFlow  start......");
        }
        parser();
        EventFlowEngine configEngine = ServiceContext.getEngine();
        if (configEngine == null) {
            throw new EventFlowException("配置无法发现buildEventFlowEngine的实现类");
        }
        if (log.isInfoEnabled()) {
            log.info("SnakerEngine be found:" + configEngine.getClass());
        }
        return configEngine.configure(this);
    }

    protected void parser() {
        if (log.isDebugEnabled()) {
            log.debug("Service parsing start......");
        }
        parser(BASE_CONFIG_FILE);
        if (!isCMB()) {
            parser(EXT_CONFIG_FILE);
            for (Map.Entry<String, Class<?>> entry : txClass.entrySet()) {
                ServiceContext.put(entry.getKey(), entry.getValue());
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("Service parsing finish......");
        }
    }

    /**
     * 解析给定resource配置，并注册到ServiceContext上下文中
     *
     * @param resource 资源
     */
    private void parser(String resource) {
        //解析所有配置节点，并实例化class指定的类
        DocumentBuilder documentBuilder = XmlHelper.createDocumentBuilder();
        try {
            if (documentBuilder != null) {
                InputStream input = StreamHelper.openStream(resource);
                if (input == null) return;
                Document doc = documentBuilder.parse(input);
                Element configElement = doc.getDocumentElement();
                NodeList nodeList = configElement.getChildNodes();
                int nodeSize = nodeList.getLength();
                for (int i = 0; i < nodeSize; i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String name = element.getAttribute("name");
                        String className = element.getAttribute("class");
                        String proxy = element.getAttribute("proxy");
                        if (StringHelper.isEmpty(name)) {
                            name = className;
                        }
                        if (ServiceContext.exist(name)) {
                            log.warn("Duplicate name is:" + name);
                            continue;
                        }
                        Class<?> clazz = ClassHelper.loadClass(className);

                        if (proxy != null && proxy.equalsIgnoreCase("transaction")) {
                            txClass.put(name, clazz);
                        } else {
                            ServiceContext.put(name, clazz);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new EventFlowException("资源解析失败，请检查配置文件[" + resource + "]", e.getCause());
        }
    }

    /**
     * is container manger Bean
     */
    public boolean isCMB() {
        return false;
    }

}
