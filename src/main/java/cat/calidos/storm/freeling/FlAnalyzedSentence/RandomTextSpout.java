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


import java.util.Map;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class RandomTextSpout extends BaseRichSpout{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SpoutOutputCollector _collector;  
	private int i;
	
	
	
	public RandomTextSpout() {
		this.i=0;
	}
	
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		 _collector = collector;      
    }
    
    @Override
    public void close() {
        
    }	

   
    public void nextTuple() { 
    	if(i<30000){
	    	i+=1;
	    	_collector.emit(new Values("Random text -> "+i));	    	
    	}
	    	    	   	
    }        

    @Override
    public void ack(Object id) {
    }

    @Override
    public void fail(Object id) {
    }

    
    public void declareOutputFields(OutputFieldsDeclarer declarer) {       
        declarer.declare(new Fields("frase")); 
	}
}
