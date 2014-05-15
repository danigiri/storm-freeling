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

package cat.calidos.storm.freeling.socket;

import java.util.Map; 

import org.jboss.netty.channel.ChannelPipelineFactory;

import backtype.storm.tuple.Values;
import cat.calidos.storm.freeling.FlAnalyzedSentence.FlAnalyzedSentence;
import cat.calidos.storm.task.SocketBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;

public class FreelingBolt extends SocketBolt {
	
	public FreelingBolt(String host, int port) {
		super(host, port);		
	}
	
	public FreelingBolt(String host, int port, Map<String, Object> bootstrapOptions) {
		super(host, port, bootstrapOptions);		
	}	
	
	@Override
	public ChannelPipelineFactory getPipelineFactory() {	
		return  new FreelingPipelineFactory(this);
	}

	
	public void handleEmit(FlAnalyzedSentence message) {		
		LOG.info("handleEmitMorfo: message="+message.getMorfologicText());			
		_collector.emit(new Values(message));			
	}
	
	@Override
 	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("FlAnalyzedSentence"));
	}
 	
 
	
}
