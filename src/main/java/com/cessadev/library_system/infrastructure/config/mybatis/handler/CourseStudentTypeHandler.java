package com.cessadev.library_system.infrastructure.config.mybatis.handler;

import com.cessadev.library_system.domain.enums.ECourseStudent;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ECourseStudent.class)
public class CourseStudentTypeHandler extends BaseTypeHandler<ECourseStudent> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, ECourseStudent parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.name());
  }

  @Override
  public ECourseStudent getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String value = rs.getString(columnName);
    return value == null ? null : ECourseStudent.valueOf(value.toUpperCase());
  }

  @Override
  public ECourseStudent getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String value = rs.getString(columnIndex);
    return value == null ? null : ECourseStudent.valueOf(value.toUpperCase());
  }

  @Override
  public ECourseStudent getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String value = cs.getString(columnIndex);
    return value == null ? null : ECourseStudent.valueOf(value.toUpperCase());
  }
}
