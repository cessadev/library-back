package com.cessadev.library_system.infrastructure.config.mybatis.handler;

import com.cessadev.library_system.domain.enums.ELoanStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ELoanStatus.class)
public class LoanStatusTypeHandler extends BaseTypeHandler<ELoanStatus> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, ELoanStatus parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.name());
  }

  @Override
  public ELoanStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String value = rs.getString(columnName);
    return value == null ? null : ELoanStatus.valueOf(value.toUpperCase());
  }

  @Override
  public ELoanStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String value = rs.getString(columnIndex);
    return value == null ? null : ELoanStatus.valueOf(value.toUpperCase());
  }

  @Override
  public ELoanStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String value = cs.getString(columnIndex);
    return value == null ? null : ELoanStatus.valueOf(value.toUpperCase());
  }
}
