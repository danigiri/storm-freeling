/**
Copyright 2014 Jaume Jané 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package cat.calidos.storm.freeling.FlAnalyzedSentence;


import cat.calidos.storm.freeling.socket.FreelingBolt;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;


public class StormFreelingTopology {
	public static void main(String[] args) {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
   
        TopologyBuilder b = new TopologyBuilder();       
        b.setSpout("RandomTextSpout", new RandomTextSpout());       
        b.setBolt("SocketBolt", new FreelingBolt(host,port)).shuffleGrouping("RandomTextSpout");         
	       
		if(args!=null && args.length > 2) { 
			Config conf = new Config();
            conf.setDebug(true);
            conf.setNumWorkers(3); 
            try {
				StormSubmitter.submitTopology(args[2], conf, b.createTopology());
			} catch (AlreadyAliveException e) {				
				e.printStackTrace();
			} catch (InvalidTopologyException e) {				
				e.printStackTrace();
			}       
           
	    }
		else {  
	        LocalCluster cluster = new LocalCluster();
			try {	
				cluster.submitTopology("test", new Config(), b.createTopology());
				Utils.sleep(1000000);	
			} finally {
				try {
				cluster.shutdown();
				} catch (Exception e) {}			
			}
		}
		
		
	
		
		
        
    }
}
