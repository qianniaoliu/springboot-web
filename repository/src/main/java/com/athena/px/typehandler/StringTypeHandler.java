package com.athena.px.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/5/22 16:00
 */
public class StringTypeHandler extends BaseTypeHandler<String> {

    public StringTypeHandler(){}

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,s + "[SL]");
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getString(s) + "[SL]";
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(i) + "[SL]";
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getString(i) + "[SL]";
    }
}
