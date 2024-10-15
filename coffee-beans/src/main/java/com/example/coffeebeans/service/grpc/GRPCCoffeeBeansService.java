package com.example.coffeebeans.service.grpc;

import com.example.coffeebeans.model.entity.RoastingBatch;
import com.example.coffeebeans.model.mapper.RoastingBatchMapper;
import com.example.coffeebeans.repository.RoastingBatchRepository;
import com.example.grpccommon.MyRequest;
import com.example.grpccommon.MyResponse;
import com.example.grpccommon.MyServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GRPCCoffeeBeansService extends MyServiceGrpc.MyServiceImplBase {

    Logger LOGGER = LoggerFactory.getLogger(GRPCCoffeeBeansService.class);

    private final RoastingBatchRepository roastingBatchRepository;
    private final RoastingBatchMapper mapper;

    @Override
    public void getData(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        LOGGER.info(request.getIdArrivalCoffeeBeans());

        RoastingBatch entity = mapper.toEntity(request);
        RoastingBatch save = roastingBatchRepository.save(entity);

        MyResponse response =
                MyResponse.newBuilder()
                        .setMessageResponse("Successful. Id = " + save.getId())
                        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
