package com.nott.practice.flink.boot;

import com.nott.practice.flink.sink.PostSQLSink;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple9;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import java.util.Properties;

public class FlinkApplication {

	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.enableCheckpointing(5000);

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("zookeeper.connect", "localhost:2181");
		properties.setProperty("group.id", "test");

		FlinkKafkaConsumer011<String> myConsumer = new FlinkKafkaConsumer011<String>("shopbox", new SimpleStringSchema(), properties);

		DataStream<String> sourceStream = env.addSource(myConsumer);
		DataStream<Tuple9<String, String, String, String, String, String, String, String, String>> messageStream = sourceStream.map(new InputMap()).filter(new NullFilter());

		messageStream.addSink(new PostSQLSink());
		env.execute("Write into PostgreSQL");
	}

	public static class NullFilter implements FilterFunction<Tuple9<String, String, String, String, String, String, String, String, String>> {
		@Override
		public boolean filter(Tuple9<String, String, String, String, String, String, String, String, String> value) throws Exception {
			return value != null;
		}
	}

	public static class InputMap implements MapFunction<String, Tuple9<String, String, String, String, String, String, String, String, String>> {
		private static final long serialVersionUID = 1L;

		@Override
		public Tuple9<String, String, String, String, String, String, String, String, String> map(String value) throws Exception {
			String[] arr = value.toLowerCase().split(",");
			if (arr.length > 4) {
				return new Tuple9<>(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8]);
			}
			return null;
		}
	}

}
