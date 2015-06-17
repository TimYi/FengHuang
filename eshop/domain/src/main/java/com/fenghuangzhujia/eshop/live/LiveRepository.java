package com.fenghuangzhujia.eshop.live;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.fenghuangzhujia.eshop.live.Live.ProjectProgress;
import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface LiveRepository extends SpecificationRepository<Live, String> {

	/**获取某种状态的直播数据*/
	Page<Live> findByStatus(ProjectProgress status, Pageable pageable);
	/**获取某个施工日期之后的直播数据*/
	@Query("select l from Live l where l.startDate>=date")
	Page<Live> findDateLater(Date date, Pageable pageable);
	
	List<Live> findByUserId(String userId);
}
