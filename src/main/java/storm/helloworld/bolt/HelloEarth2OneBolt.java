package storm.helloworld.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class HelloEarth2OneBolt extends BaseRichBolt {

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
		value += ", The Flash in Earth 2 is ";
		
		collector.emit(new Values(value));
		collector.ack(tuple);
	}

	@Override
	public void cleanup() {
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("gotham"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
