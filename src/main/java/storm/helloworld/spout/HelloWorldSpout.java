package storm.helloworld.spout;

import java.io.IOException;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import storm.helloworld.EchoClient;

//public class HelloWorldSpout implements IRichSpout {
public class HelloWorldSpout extends BaseRichSpout {

	private static final long serialVersionUID = 1L;
	private SpoutOutputCollector collector;
	private EchoClient echoClient;
	
	@Override
	public void open(@SuppressWarnings("rawtypes") Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void close() {
		System.out.println("-------- >close method");
		try {
			echoClient.close();
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
	}

	@Override
	public void activate() {
		System.out.println("-------- >activate method");
		echoClient = new EchoClient();
		try {
			echoClient.start();
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
	}

	@Override
	public void deactivate() {
		System.out.println("-------- >deactivate method");
	}

	@Override
	public void nextTuple() {
		String value = null;
		try {
			echoClient.read();
			value = echoClient.getValue();
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		this.collector.emit(new Values(value));		
	}
	
	@Override
	public void ack(Object msgId) {
		System.out.println("-------- >ack method");
	}
	
	@Override
	public void fail(Object msgId) {
		System.out.println("-------- >fail method");
	}
	
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		System.out.println("-------- >fields method");
		declarer.declare(new Fields("field-dc-comics"));
	}
	
	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}


}
