package info.nemoworks.grass.storage.arcadedb.mapper;

import com.arcadedb.schema.VertexType;
import info.nemoworks.grass.meta.GType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TypeMapper {


    @Mapping(source = "name", target = "")
    public VertexType vertexTypeMapper(GType gType);

}
