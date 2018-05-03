package com.athena.px.springbootweb.converter;

import com.athena.px.springbootweb.domain.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/4/23 9:39
 */
public class PropertiesJsonHttpMessageConverter extends AbstractHttpMessageConverter<User> {

    public PropertiesJsonHttpMessageConverter() {
        super(MediaType.valueOf("application/properties"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(User.class);
    }

    @Override
    protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream inputStream = inputMessage.getBody();
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream,getDefaultCharset()));
        User user = new User();
        user.setId(Integer.valueOf(properties.getProperty("user.id")));
        user.setName(properties.getProperty("user.name"));
        return user;
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = outputMessage.getBody();
        Properties properties = new Properties();
        properties.setProperty("user.id",String.valueOf(user.getId()));
        properties.setProperty("user.name",user.getName());
        properties.store(new OutputStreamWriter(outputStream,getDefaultCharset()),"user-json/properties");
    }
}
