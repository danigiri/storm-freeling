/**
Copyright 2014 Daniel Giribet <dani - calidos.cat>
Copyright 2014 Jaume Jan� 

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


import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import cat.calidos.storm.freeling.FlAnalyzedSentence.FlAnalyzedSentence;

public class StringFreelingDecoder extends OneToOneDecoder {
	 // TODO Use CharsetDecoder instead.
    private final Charset charset;

    /**
     * Creates a new instance with the current system character set.
     */
    public StringFreelingDecoder() {
        this(Charset.defaultCharset());
    }

    /**
     * Creates a new instance with the specified character set.
     */
    public StringFreelingDecoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
    }

   

    @Override
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        if (!(msg instanceof ChannelBuffer)) {        	
            return new FlAnalyzedSentence(((ChannelBuffer) msg).toString(charset));
        }      
        return new FlAnalyzedSentence(((ChannelBuffer) msg).toString(charset));
    }
}
