package com.example.coffeebeans.model.mapper;


import com.example.coffeebeans.model.entity.RoastingBatch;
import com.example.grpccommon.MyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RoastingBatchMapper extends Mappable<RoastingBatch, MyRequest> {
    @Override
    RoastingBatch toEntity(MyRequest myRequest);

    @Override
    MyRequest toResponse(RoastingBatch roastingBatch);
}
