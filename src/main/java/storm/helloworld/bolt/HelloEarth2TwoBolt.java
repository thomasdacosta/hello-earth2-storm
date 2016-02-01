package storm.helloworld.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import storm.helloworld.EchoFile;

public class HelloEarth2TwoBolt extends BaseRichBolt {
	
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
		String fileName = System.currentTimeMillis() + ".txt"; 
		value += "Jay Garrick.";

		EchoFile.file(fileName, value);
		System.out.println("-------------> arquivo criado: " + fileName);
		collector.ack(tuple);
	}

	@Override
	public void cleanup() {
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
