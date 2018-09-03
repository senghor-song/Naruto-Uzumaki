package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.EmpStoreApply;

public interface EmpStoreApplyMapper {
    /**
     *
     * @mbg.generated 2018-07-31
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbg.generated 2018-07-31
     */
    int insert(EmpStoreApply record);

    /**
     *
     * @mbg.generated 2018-07-31
     */
    int insertSelective(EmpStoreApply record);

    /**
     *
     * @mbg.generated 2018-07-31
     */
    EmpStoreApply selectByPrimaryKey(String id);

    /**
     *
     * @mbg.generated 2018-07-31
     */
    int updateByPrimaryKeySelective(EmpStoreApply record);

    /**
     *
     * @mbg.generated 2018-07-31
     */
    int updateByPrimaryKey(EmpStoreApply record);
}