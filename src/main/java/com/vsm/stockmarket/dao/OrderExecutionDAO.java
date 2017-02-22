package com.vsm.stockmarket.dao;

import javax.persistence.Query;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.vsm.stockmarket.domain.entity.OrderExecution;
import com.vsm.stockmarket.domain.entity.OrderMaster;
import com.vsm.stockmarket.exception.DAOException;
import com.vsm.stockmarket.exception.DBRecordNotFoundException;

@Service("orderExecutionDAO")
@Repository
public class OrderExecutionDAO extends BaseDAO {

	public OrderExecution findByOrderMaster(OrderMaster orderMaster)
			throws DAOException {
		try {
			Query q = entityManager
					.createQuery("select e from OrderExecution e where e.orderMaster = ?");
			q.setParameter(1, orderMaster);
			return (OrderExecution) q.getSingleResult();
		} catch (EmptyResultDataAccessException e) {
			throw new DBRecordNotFoundException(e);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
