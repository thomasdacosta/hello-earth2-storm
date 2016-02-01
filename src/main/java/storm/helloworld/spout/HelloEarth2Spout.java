package storm.helloworld.spout;

import java.io.IOException;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import storm.helloworld.EchoClient;

public class HelloEarth2Spout extends BaseRichSpout {

	private static final long serialVersionUID = 1L;
	private SpoutOutputCollector collector;
	private EchoClient echoClient;
	
	@Override
	public void open(@SuppressWarnings("rawtypes") Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void close() {
		try {
			echoClient.close();
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}

	@Override
	public void activate() {
		echoClient = new EchoClient();
		try {
			echoClient.start();
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}

	@Override
	public void deactivate() {
	}

	@Override
	public void nextTuple() {
		String value = null;
		String id = System.currentTimeMillis() + "";
		try {
			echoClient.read();
			value = echoClient.getValue();
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		this.collector.emit(new Values(value), id);		
	}
	
	@Override
	public void ack(Object msgId) {
		System.out.println("-------------> ack: " + msgId);
	}
	
	@Override
	public void fail(Object msgId) {
	}
	
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("dccomics"));
	}
	
	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
