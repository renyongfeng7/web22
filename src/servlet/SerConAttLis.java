package servlet;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
/*监听器*/
public class SerConAttLis implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("add"+se.getName()+"------------"+se.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("remove"+se.getName()+"------------"+se.getValue());

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("replace"+se.getName()+"------------"+se.getValue());

    }
}
