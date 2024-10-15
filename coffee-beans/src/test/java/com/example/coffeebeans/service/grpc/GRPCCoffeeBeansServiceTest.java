package com.example.coffeebeans.service.grpc;

import com.example.coffeebeans.model.entity.RoastingBatch;
import com.example.coffeebeans.model.mapper.RoastingBatchMapper;
import com.example.coffeebeans.repository.RoastingBatchRepository;
import com.example.grpccommon.MyRequest;
import com.example.grpccommon.MyResponse;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GRPCCoffeeBeansServiceTest {
    @Mock
    private RoastingBatchRepository roastingBatchRepository;

    @Mock
    private RoastingBatchMapper mapper;

    @Mock
    private StreamObserver<MyResponse> responseObserver;

    @InjectMocks
    private GRPCCoffeeBeansService grpcCoffeeBeansService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getData_shouldSaveRoastingBatchAndReturnResponse() {

        MyRequest request = MyRequest.newBuilder()
                .setIdArrivalCoffeeBeans("1")
                .setVariety("Arabica")
                .setQuantityBag(10)
                .setCountryOfOrigin("Brazil")
                .setOutputWeight(500)
                .setRoastingTeamId("123e4567-e89b-12d3-a456-426614174000")
                .build();

        RoastingBatch roastingBatch = new RoastingBatch();
        RoastingBatch savedBatch = new RoastingBatch();
        savedBatch.setId(1L);

        when(mapper.toEntity(request)).thenReturn(roastingBatch);
        when(roastingBatchRepository.save(roastingBatch)).thenReturn(savedBatch);

        ArgumentCaptor<MyResponse> responseCaptor = ArgumentCaptor.forClass(MyResponse.class);

        grpcCoffeeBeansService.getData(request, responseObserver);

        verify(roastingBatchRepository).save(roastingBatch);
        verify(mapper).toEntity(request);
        verify(responseObserver).onNext(responseCaptor.capture());
        verify(responseObserver).onCompleted();

        MyResponse capturedResponse = responseCaptor.getValue();
        assertEquals("Successful. Id = 1", capturedResponse.getMessageResponse());
    }

    @Test
    void getData_shouldHandleNullValuesGracefully() {

        MyRequest request = MyRequest.newBuilder().build();
        RoastingBatch roastingBatch = new RoastingBatch();
        RoastingBatch savedBatch = new RoastingBatch();
        savedBatch.setId(2L);

        when(mapper.toEntity(request)).thenReturn(roastingBatch);
        when(roastingBatchRepository.save(roastingBatch)).thenReturn(savedBatch);

        ArgumentCaptor<MyResponse> responseCaptor = ArgumentCaptor.forClass(MyResponse.class);

        grpcCoffeeBeansService.getData(request, responseObserver);

        verify(roastingBatchRepository).save(roastingBatch);
        verify(mapper).toEntity(request);
        verify(responseObserver).onNext(responseCaptor.capture());
        verify(responseObserver).onCompleted();

        MyResponse capturedResponse = responseCaptor.getValue();
        assertEquals("Successful. Id = 2", capturedResponse.getMessageResponse());
    }
}
