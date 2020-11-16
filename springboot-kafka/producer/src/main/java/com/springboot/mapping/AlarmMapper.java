package com.springboot.mapping;

import com.springboot.model.AlarmDTO;
import com.springboot.model.AlarmJSON;
import com.springboot.model.MetadataJSON;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface AlarmMapper {

    AlarmDTO jsonToDto(MetadataJSON metadataJSON);

}
