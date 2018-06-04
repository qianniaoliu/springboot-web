package com.athena.px;

import static org.junit.Assert.assertTrue;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.athena.px.config.DubboConfig;
import com.iu.sl.api.BlogService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    @Ignore
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void dubbo(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DubboConfig.class);
        context.refresh();
        /*BlogService blogService = context.getBean(BlogService.class);
        System.out.println(blogService.toString());*/
    }
}
