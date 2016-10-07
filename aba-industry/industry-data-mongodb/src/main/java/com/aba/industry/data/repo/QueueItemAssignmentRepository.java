/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.data.repo;

import com.aba.data.domain.Corporation;
import com.aba.industry.domain.Producer;
import com.aba.industry.domain.QueueItem;
import com.aba.industry.domain.QueueItemAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by maurerit on 10/6/16.
 */
public interface QueueItemAssignmentRepository extends MongoRepository<QueueItemAssignment, String> {
    List<QueueItemAssignment> findByProducer ( Producer producer );

    List<QueueItemAssignment> findByCorporation ( Corporation corporation );

    List<QueueItemAssignment> findByQueueItem ( QueueItem queueItem );

    QueueItemAssignment findByProducerAndQueueItem ( Producer producer, QueueItem queuItem );
}
