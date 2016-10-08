/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.data.service.impl;

import com.aba.industry.data.repo.QueueItemAssignmentRepository;
import com.aba.industry.data.repo.QueueItemRepository;
import com.aba.industry.data.service.QueueService;
import com.aba.industry.domain.Producer;
import com.aba.industry.domain.QueueItem;
import com.aba.industry.domain.QueueItemAssignment;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

/**
 * Created by maurerit on 10/2/16.
 */
public class QueueServiceImpl implements QueueService {
    @Autowired
    private QueueItemAssignmentRepository queueItemAssignmentRepository;

    @Autowired
    private QueueItemRepository queueItemRepository;

    @Override
    public void assignQueueItem ( QueueItem queueItem, Producer producer, Long quantity ) {
        QueueItemAssignment assignment = queueItemAssignmentRepository.findByProducerAndQueueItem( producer,
                                                                                                   queueItem );

        if ( assignment == null ) {
            assignment = new QueueItemAssignment();
            assignment.setProducer( producer );
            assignment.setQueueItem( queueItem );
        }

        assignment.setQuantity( assignment.getQuantity() + quantity );

        addQueueItemAssignmentToQueueItem( assignment, queueItem );
        queueItemAssignmentRepository.save( assignment );
    }

    @Override
    public void unassignQueueItem ( QueueItem queueItem, Producer producer, Long quantity ) {
        QueueItemAssignment assignment = queueItemAssignmentRepository.findByProducerAndQueueItem( producer,
                                                                                                   queueItem );

        if ( assignment != null ) {
            Long assignedQuantity = assignment.getQuantity();
            assignedQuantity -= quantity;

            if ( assignedQuantity <= 0 ) {
                queueItem.getAssignments()
                         .remove( assignment );
                queueItemAssignmentRepository.delete( assignment );
                queueItemRepository.save( queueItem );

            }
            else {
                assignment.setQuantity( assignedQuantity );
                addQueueItemAssignmentToQueueItem( assignment, queueItem );
                queueItemAssignmentRepository.save( assignment );
                queueItemRepository.save( queueItem );
            }
        }
    }

    @Override
    public List<QueueItemAssignment> getQueueItemAssignments ( Producer producer ) {
        return queueItemAssignmentRepository.findByProducer( producer );
    }

    @Override
    public QueueItem createQueueItem ( Long typeId, Long quantity, Long buildTime ) {
        LocalDate localDate = LocalDate.now();
        Integer year = localDate.getYear();
        Integer month = localDate.getMonthValue();

        return createQueueItem( typeId, quantity, buildTime, year, month );
    }

    @Override
    public QueueItem createQueueItem ( Long typeId, Long quantity, Long buildTime, Integer year, Integer month ) {
        QueueItem queueItem = queueItemRepository.findByTypeIdAndYearAndMonth( typeId, year, month );

        if ( queueItem != null ) {
            throw new IllegalArgumentException(
                    "Queue Item for this year(" + year + "), month(" + month + "), and typeId(" + typeId + ") already" +
                            " exists.  Use updateQueueItem instead." );
        }

        queueItem = new QueueItem();
        queueItem.setTypeId( typeId );
        queueItem.setYear( year );
        queueItem.setMonth( month );
        queueItem.setQuantity( quantity );

        return queueItemRepository.save( queueItem );
    }

    @Override
    public QueueItem updateQueueItem ( QueueItem queueItem ) {
        if ( queueItem == null || queueItem.getId() == null || "".equalsIgnoreCase( queueItem.getId() ) ) {
            throw new IllegalArgumentException( "Invalid Queue Item passed to udpated service method." );
        }

        QueueItem existingQueueItem = queueItemRepository.findOne( queueItem.getId() );

        if ( existingQueueItem == null ) {
            throw new IllegalArgumentException( "No existing Queue Item to edit, call createQueueItem instead." );
        }

        existingQueueItem.setTypeId( queueItem.getTypeId() );
        existingQueueItem.setYear( queueItem.getYear() );
        existingQueueItem.setMonth( queueItem.getMonth() );
        existingQueueItem.setQuantity( queueItem.getQuantity() );
        existingQueueItem.setBuildTime( queueItem.getBuildTime() );

        return queueItemRepository.save( queueItem );
    }

    @Override
    public List<QueueItem> getQueueItems ( Integer year, Integer month ) {
        return queueItemRepository.findByYearAndMonth( year, month );
    }

    private void addQueueItemAssignmentToQueueItem ( QueueItemAssignment queueItemAssignment, QueueItem queueItem ) {
        if ( queueItem.getAssignments() == null ) {
            queueItem.setAssignments( new HashSet<>() );
        }
        queueItem.getAssignments()
                 .add( queueItemAssignment );
    }
}
