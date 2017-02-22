package com.vsm.stockmarket.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.vsm.stockmarket.constants.IApplicationConstants;
import com.vsm.stockmarket.domain.entity.OrderMaster;
import com.vsm.stockmarket.domain.entity.UserMaster;
import com.vsm.stockmarket.exception.DAOException;

@Service("orderMasterDAO")
@Repository
public class OrderMasterDAO extends BaseDAO {
	public List<OrderMaster> findAllPendingOrders() throws DAOException {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("status", IApplicationConstants.ORDER_STATUS_PENDING);
		return findByFilter(OrderMaster.class, criteria);
	}

	public List<OrderMaster> findAllCompletedOrders(UserMaster userMaster)
			throws DAOException {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("userMaster", userMaster);
		criteria.put("status", IApplicationConstants.ORDER_STATUS_COMPLETED);
		return findByFilter(OrderMaster.class, criteria);
	}

	public List<OrderMaster> findAllCompletedOrdersByLeaugeUser(
			long leagueUserId) throws DAOException {
		try {
			Query q = entityManager
					.createQuery("select e from OrderMaster e join fetch e.orderExecutions where e.status = ?1 and e.leagueUser.leagueUserId = ?2 ");
			q.setParameter(1, IApplicationConstants.ORDER_STATUS_COMPLETED);
			q.setParameter(2, leagueUserId);
			return q.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<OrderMaster> findAllOrdersByLeaugeUser(long leagueUserId)
			throws DAOException {
		try {
			Query q = entityManager
					.createQuery("select e from OrderMaster e where e.leagueUser.leagueUserId = ?1 ");
			q.setParameter(1, leagueUserId);
			return q.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
