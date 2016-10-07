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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by maurerit on 10/6/16.
 */
@RunWith( MockitoJUnitRunner.class )
public class QueueServiceImplTest {
    @InjectMocks
    private QueueService queueService = new QueueServiceImpl();

    @Mock
    private QueueItemAssignmentRepository queueItemAssignmentRepository;

    @Mock
    private QueueItemRepository queueItemRepository;

    private Producer                            producer;
    private QueueItem                           queueItem;
    private QueueItemAssignment                 queueItemAssignment;
    private ArgumentCaptor<Producer>            producerCaptor;
    private ArgumentCaptor<QueueItem>           queueItemServiceCaptor;
    private ArgumentCaptor<QueueItem>           queueItemRepoCaptor;
    private ArgumentCaptor<QueueItemAssignment> queueItemAssignmentCaptor;

    @Before
    public void setUp ( ) throws Exception {
        producer = new Producer();
        queueItem = new QueueItem();
        queueItemAssignment = new QueueItemAssignment();
        producerCaptor = ArgumentCaptor.forClass( Producer.class );
        queueItemServiceCaptor = ArgumentCaptor.forClass( QueueItem.class );
        queueItemRepoCaptor = ArgumentCaptor.forClass( QueueItem.class );
        queueItemAssignmentCaptor = ArgumentCaptor.forClass( QueueItemAssignment.class );
    }

    @Test
    public void assignQueueItem ( ) throws Exception {
        queueService.assignQueueItem( queueItem, producer, 20L );

        verify( queueItemAssignmentRepository, times( 1 ) ).findByProducerAndQueueItem( producerCaptor.capture(),
                                                                                        queueItemServiceCaptor
                                                                                                .capture() );
        verify( queueItemAssignmentRepository, times( 1 ) ).save( queueItemAssignmentCaptor.capture() );
        assertNotNull( queueItem.getAssignments() );
        assertEquals( 1, queueItem.getAssignments()
                                  .size() );
        assertTrue( queueItem.getAssignments()
                             .contains( queueItemAssignmentCaptor.getValue() ) );
    }

    @Test
    public void assignDuplicateQueueItem ( ) throws Exception {
        queueItem.setQuantity( 30L );
        queueItemAssignment.setQuantity( 10L );

        when( queueItemAssignmentRepository.findByProducerAndQueueItem( producer, queueItem ) ).thenReturn(
                queueItemAssignment );

        queueService.assignQueueItem( queueItem, producer, 10L );

        verify( queueItemAssignmentRepository, times( 1 ) ).findByProducerAndQueueItem( producerCaptor.capture(),
                                                                                        queueItemServiceCaptor
                                                                                                .capture() );
        verify( queueItemAssignmentRepository, times( 1 ) ).save( queueItemAssignmentCaptor.capture() );
        assertEquals( 20L, queueItemAssignmentCaptor.getValue()
                                                    .getQuantity()
                                                    .longValue() );
        assertNotNull( queueItem.getAssignments() );
        assertEquals( 1, queueItem.getAssignments()
                                  .size() );
        assertTrue( queueItem.getAssignments()
                             .contains( queueItemAssignmentCaptor.getValue() ) );
    }

    @Test
    public void unassignQueueItemPartial ( ) throws Exception {
        queueItem.setQuantity( 30L );
        queueItemAssignment.setQuantity( 25L );
        queueItem.setAssignments( new HashSet<>() );
        queueItem.getAssignments()
                 .add( queueItemAssignment );

        when( queueItemAssignmentRepository.findByProducerAndQueueItem( producer, queueItem ) ).thenReturn(
                queueItemAssignment );

        queueService.unassignQueueItem( queueItem, producer, 20L );

        verify( queueItemAssignmentRepository, times( 1 ) ).findByProducerAndQueueItem( producerCaptor.capture(),
                                                                                        queueItemServiceCaptor
                                                                                                .capture() );
        verify( queueItemAssignmentRepository, times( 1 ) ).save( queueItemAssignmentCaptor.capture() );
        verify( queueItemRepository, times( 1 ) ).save( queueItemRepoCaptor.capture() );
        assertEquals( 5L, queueItemAssignmentCaptor.getValue()
                                                   .getQuantity()
                                                   .longValue() );
        assertNotNull( queueItem.getAssignments() );
        assertTrue( queueItem.getAssignments()
                             .contains( queueItemAssignmentCaptor.getValue() ) );
    }

    @Test
    public void unassignQueueItemFull ( ) throws Exception {
        queueItem.setQuantity( 30L );
        queueItemAssignment.setQuantity( 25L );
        queueItem.setAssignments( new HashSet<>() );
        queueItem.getAssignments()
                 .add( queueItemAssignment );

        when( queueItemAssignmentRepository.findByProducerAndQueueItem( producer, queueItem ) ).thenReturn(
                queueItemAssignment );

        queueService.unassignQueueItem( queueItem, producer, 25L );

        verify( queueItemAssignmentRepository, times( 1 ) ).findByProducerAndQueueItem( producerCaptor.capture(),
                                                                                        queueItemServiceCaptor
                                                                                                .capture() );
        verify( queueItemAssignmentRepository, times( 1 ) ).delete( queueItemAssignmentCaptor.capture() );
        verify( queueItemRepository, times( 1 ) ).save( queueItemRepoCaptor.capture() );
        assertEquals( queueItemAssignment, queueItemAssignmentCaptor.getValue() );
        assertTrue( queueItem.getAssignments()
                             .isEmpty() );
    }

    @Test
    public void getQueueItemAssignments ( ) throws Exception {
        when( queueItemAssignmentRepository.findByProducer( producer ) ).thenReturn(
                Arrays.asList( queueItemAssignment ) );

        List<QueueItemAssignment> assignments = queueService.getQueueItemAssignments( producer );

        assertNotNull( assignments );
        assertEquals( queueItemAssignment, assignments.get( 0 ) );
    }

    @Test
    public void createQueueItem ( ) throws Exception {
        fail( "Implement me" );
    }

    @Test
    public void createQueueItem1 ( ) throws Exception {
        fail( "Implement me" );
    }

    @Test
    public void updateQueueItem ( ) throws Exception {
        fail( "Implement me" );
    }

    @Test
    public void getQueueItems ( ) throws Exception {
        fail( "Implement me" );
    }

}