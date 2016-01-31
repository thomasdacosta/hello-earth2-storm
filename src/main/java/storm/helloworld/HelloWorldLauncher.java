package storm.helloworld;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import storm.helloworld.bolt.HelloWorldOneBolt;
import storm.helloworld.bolt.HelloWorldTwoBolt;
import storm.helloworld.spout.HelloWorldSpout;

public class HelloWorldLauncher {
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		config.setDebug(true);
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("HelloWorldSpout", new HelloWorldSpout());
		
		builder.setBolt("HelloWorldOneBolt", new HelloWorldOneBolt())
			.shuffleGrouping("HelloWorldSpout");

		builder.setBolt("HelloWorldTwoBolt", new HelloWorldTwoBolt())
			.shuffleGrouping("HelloWorldOneBolt");
		
//		LocalCluster cluster = new LocalCluster();
//		cluster.submitTopology("HelloWorld", config, builder.createTopology());
//		Thread.sleep(10000);
//		cluster.deactivate("HelloWorld");
//		cluster.killTopology("HelloWorld");
		
		StormSubmitter.submitTopology("HelloWorld", config, builder.createTopology());
	}

}
