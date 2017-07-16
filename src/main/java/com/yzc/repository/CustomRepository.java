package com.yzc.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean//指明当前接口不是领域类的接口
public interface CustomRepository<T,ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {
	
	/**
	 * 自动模糊查询，即对于任意的实体对象进行查询，对象里面有几个值就查询几个值，值为String类型时进行模糊查询，其他类型使用自动等于查询，没有值就查询全部
	 * @author yzc
	 * @date 2017年5月9日 
	 * @param example
	 * @param pageable
	 * @return
	 */
	Page<T> findByAuto(T example,Pageable pageable);

}
