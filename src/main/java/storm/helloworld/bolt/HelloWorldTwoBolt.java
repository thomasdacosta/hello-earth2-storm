package storm.helloworld.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class HelloWorldTwoBolt extends BaseRichBolt {
	
	private static final long serialVersionUID = -7269157525422828847L;
	private OutputCollector collector;

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple tuple) {
		String value = tuple.getString(0);
		value += " and Bruce Wayne.";
		System.out.println("-------- > BOLT2:" + value);
		collector.ack(tuple);
	}

	@Override
	public void cleanup() {
		System.out.println("-------- >cleanup method");
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		System.out.println("-------- >declareOutputFields method");
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
