package storm.helloworld.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

//public class HelloWorldOneBolt implements IRichBolt {
public class HelloWorldOneBolt extends BaseRichBolt {

	private static final long serialVersionUID = 3190593874345741440L;
	private OutputCollector collector;

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple tuple) {
		String value = tuple.getString(0);
		value = value.replaceAll("\n", "");
		value = value.replaceAll("\r", "");
		value += " You are Batman";
		System.out.println("-------- > BOLT1:" + value);
		collector.emit(new Values(value));
		collector.ack(tuple);
	}

	@Override
	public void cleanup() {
		System.out.println("-------- >cleanup method");
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		System.out.println("-------- >declareOutputFields method");
		declarer.declare(new Fields("field-dc-comics"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
