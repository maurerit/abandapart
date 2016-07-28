/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bus;

import com.aba.data.TypedJsonMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IndustryDirectorConnection {
    /**
     * Push a message to the router connection that the industry-director has specifically
     * for clients to request data from some other services bound up to the service provider
     * router connection.
     *
     * @param message
     * @return
     */
    public String requestReply ( TypedJsonMessage message ) throws JsonProcessingException;

    /**
     * Publish a message for all who might be subscribing to the topic to retrieve.
     *
     * @param topic
     * @param messageBody
     */
    void sendMessage ( String topic, TypedJsonMessage messageBody ) throws JsonProcessingException;
}
