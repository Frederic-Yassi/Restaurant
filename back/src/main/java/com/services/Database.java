package com.services;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {

    public Connection getConnection() throws SQLException ;

}
