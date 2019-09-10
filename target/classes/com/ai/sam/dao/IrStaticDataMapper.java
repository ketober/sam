package com.ai.sam.dao;
import tk.mybatis.mapper.common.Mapper;
import com.ai.sam.domain.IrStaticData;
import com.ai.sam.domain.IrStaticDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrStaticDataMapper extends Mapper<IrStaticData>  {
    int countByExample(IrStaticDataExample example);

    int deleteByExample(IrStaticDataExample example);

    int insert(IrStaticData record);

    int insertSelective(IrStaticData record);

    List<IrStaticData> selectByExample(IrStaticDataExample example);

    int updateByExampleSelective(@Param("record") IrStaticData record, @Param("example") IrStaticDataExample example);

    int updateByExample(@Param("record") IrStaticData record, @Param("example") IrStaticDataExample example);

    /**
     * 查询静态数据
     * @param codeType
     * @return
     * @throws Exception
     */
    List<IrStaticData> retrieveStaticData(String codeType) throws Exception;
}
