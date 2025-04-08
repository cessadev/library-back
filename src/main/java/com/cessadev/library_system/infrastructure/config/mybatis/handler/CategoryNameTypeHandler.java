package com.cessadev.library_system.infrastructure.config.mybatis.handler;

import com.cessadev.library_system.domain.enums.ECategoryName;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ECategoryName.class)
public class CategoryNameTypeHandler extends BaseTypeHandler<ECategoryName> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, ECategoryName parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.name());
  }

  @Override
  public ECategoryName getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String value = rs.getString(columnName);
    return value == null ? null : ECategoryName.valueOf(value.toUpperCase());
  }

  @Override
  public ECategoryName getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String value = rs.getString(columnIndex);
    return value == null ? null : ECategoryName.valueOf(value.toUpperCase());
  }

  @Override
  public ECategoryName getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String value = cs.getString(columnIndex);
    return value == null ? null : ECategoryName.valueOf(value.toUpperCase());
  }
}
