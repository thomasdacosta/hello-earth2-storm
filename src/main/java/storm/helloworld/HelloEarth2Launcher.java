package storm.helloworld;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import storm.helloworld.bolt.HelloEarth2OneBolt;
import storm.helloworld.bolt.HelloEarth2TwoBolt;
import storm.helloworld.spout.HelloEarth2Spout;

public class HelloEarth2Launcher {
	
	public static void main(String[] args) throws Exception {
		Config config = new Config();
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("HelloEarth2Spout", new HelloEarth2Spout());
		
		builder.setBolt("HelloEarth2OneBolt", new HelloEarth2OneBolt())
			.shuffleGrouping("HelloEarth2Spout");

		builder.setBolt("HelloEarth2TwoBolt", new HelloEarth2TwoBolt())
			.shuffleGrouping("HelloEarth2OneBolt");
		
//		LocalCluster cluster = new LocalCluster();
//		cluster.submitTopology("HelloEarth2", config, builder.createTopology());
		
		StormSubmitter.submitTopology("HelloEarth2", config, builder.createTopology());
	}

}
