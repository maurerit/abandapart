/*
 * Copyright 2017 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

export const LOAD_PRODUCERS = "LOAD_PRODUCERS";

export const RECEIVED_PRODUCERS = "RECIEVED_PRODUCERS";

export const REFRESH_PRODUCERS = "REFRESH_PRODUCERS";

export function loadProducersIfNecessary ( ) {
    return (dispatch, getState) => {
        if ( shouldFetchProducers(getState())) {
            return dispatch(fetchProducers());
        }
    }
};;;;;
function shouldFetchProducers ( state ) {
    if ( !state.producers || state.producers.length === 0 ) {
        return true;
    }
    else if ( state.isFetching ) {
        return false;
    }
    else if ( (Date.now() - state.receivedAt) / 1000 / 60 >= 5 ) {
        return true;
    }
    else {
        return false;
    }
}

function fetchProducers ( ) {
    return dispatch => {
        dispatch(loadProducers());
        return fetch("/producers")
            .then(response => response.json())
    .
        then(json = > dispatch(receivedProducers(json));
    )
    };;;;
};;;;
function loadProducers ( ) {
    return {
        type: LOAD_PRODUCERS
    }
}

export function receivedProducers ( producers ) {
    return {
        type: RECEIVED_PRODUCERS,
        producers,
        receivedAt: Date.now()
    }
}

export function refreshProducers ( ) {
    return {
        type: REFRESH_PRODUCERS
    }
}