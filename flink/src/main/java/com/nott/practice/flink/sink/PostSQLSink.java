package com.nott.practice.flink.sink;

import com.nott.practice.flink.config.BaseConf;
import org.apache.flink.api.java.tuple.Tuple9;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/2/25 16:23
 * @Modified By:
 **/
public class PostSQLSink extends RichSinkFunction<Tuple9<String, String, String, String, String, String, String, String, String>> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public void open(Configuration parameters) throws Exception {
        Class.forName(BaseConf.DRIVERNAME);
        connection = DriverManager.getConnection(BaseConf.URL, BaseConf.USERNAME, BaseConf.PASSWORD);
        String sql = "insert into public.log_info(_timestamp, level_value, _version, thread_name," +
                " host, _level, _message, logger_name, port) values (?,?,?,?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        super.open(parameters);
    }

    @Override
    public void invoke(Tuple9<String, String, String, String, String, String, String, String, String> value) {
        try {
            preparedStatement.setString(1, value.f0);
            preparedStatement.setString(2, value.f1);
            preparedStatement.setString(3, value.f2);
            preparedStatement.setString(4, value.f3);
            preparedStatement.setString(5, value.f4);
            preparedStatement.setString(6, value.f5);
            preparedStatement.setString(7, value.f6);
            preparedStatement.setString(8, value.f7);
            preparedStatement.setString(9, value.f8);
            System.out.println("Start insert");
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
