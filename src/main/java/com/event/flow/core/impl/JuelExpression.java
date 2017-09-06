
package com.event.flow.core.impl;

import java.util.Map;
import java.util.Map.Entry;

import com.event.flow.core.Expression;
import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;
import javax.el.ExpressionFactory;


public class JuelExpression implements Expression {
    ExpressionFactory factory = new ExpressionFactoryImpl();

    @SuppressWarnings("unchecked")
    public <T> T eval(Class<T> T, String expr, Map<String, Object> args) {
        SimpleContext context = new SimpleContext();
        for (Entry<String, Object> entry : args.entrySet()) {
            context.setVariable(entry.getKey(), factory.createValueExpression(entry.getValue(), Object.class));
        }
        return (T) factory.createValueExpression(context, expr, T).getValue(context);
    }
}
