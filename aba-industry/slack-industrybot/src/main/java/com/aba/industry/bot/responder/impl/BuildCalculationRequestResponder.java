/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bot.responder.impl;

import com.aba.industry.bot.responder.RequestResponder;
import com.aba.industry.bus.model.BuildCalculationRequest;
import com.aba.industry.model.BuildCalculationResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

/**
 * Created by maurerit on 8/7/16.
 */
public class BuildCalculationRequestResponder implements RequestResponder<BuildCalculationRequest,
        BuildCalculationResult> {
    @Async
    @Override
    public Future<BuildCalculationResult> respond ( BuildCalculationRequest buildCalculationRequest ) {
        BuildCalculationResult result = null;


        return new AsyncResult<>( result );
    }
}
