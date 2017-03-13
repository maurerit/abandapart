/*
 * Copyright 2017 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
import {LOAD_PRODUCERS, RECEIVED_PRODUCERS, REFRESH_PRODUCERS} from "./Producers.actions";

export default function producers(state = {
    isFetching: false,
    producers: [],
    receivedAt: Date.now()
}, action) {
    let newState = {};
    switch (action.type) {
        case LOAD_PRODUCERS:
            newState = Object.assign({}, state, {
                isFetching: true
            });
            return newState;
        case RECEIVED_PRODUCERS:
            newState = Object.assign({}, state, {
                isFetching: false,
                producers: action.producers,
                receivedAt: action.receivedAt,
            });
            return newState;
        case REFRESH_PRODUCERS:
            newState = Object.assign({}, state, {
                isFetching: false,
                producers: action.producers,
                receivedAt: 0
            });
            return newState;
        default:
            return state;
    }
}