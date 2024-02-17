package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.Sub;
import com.openclassrooms.mddapi.dto.SubDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SubMapper extends EntityMapper<SubDTO, Sub> {
}