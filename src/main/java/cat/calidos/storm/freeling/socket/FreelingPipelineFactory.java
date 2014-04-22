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


import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;



public  class FreelingPipelineFactory  implements ChannelPipelineFactory{
	private static final int	DEFAULT_FRAME_SIZE	= 16384;

	private static final StringFreelingDecoder	_stringFreelingDecoder = new StringFreelingDecoder();	// shareable
	private static final StringFreelingEncoder	_stringFreelingEncoder = new StringFreelingEncoder();	// shareable

	private FreelingBolt	_socketBolt;

	public FreelingPipelineFactory(FreelingBolt socketBolt) {
			_socketBolt = socketBolt;
	}

	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
	         
		boolean stripDelimiter = true;
	    pipeline.addLast("framer", new DelimiterBasedFrameDecoder(
	    								DEFAULT_FRAME_SIZE, 
	        		 					stripDelimiter, 
	        		 					Delimiters.nulDelimiter())); 
		pipeline.addLast("decoder", _stringFreelingDecoder);	
		pipeline.addLast("encoder", _stringFreelingEncoder);    
	    pipeline.addLast("handler", new MultiLineBasedClientHandler(_socketBolt)); 
  
	    return pipeline;
     }
	
	 

	

}
