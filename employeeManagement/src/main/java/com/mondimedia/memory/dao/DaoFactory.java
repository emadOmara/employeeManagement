
package com.mondimedia.memory.dao;

import com.mondimedia.memory.common.BusinessException;
import com.mondimedia.memory.common.Constants;

/**
 * General Dao Factory
 * 
 * @author <a href="mailto:emad.omara85@gmail.com">Emad Omara</a>
 *
 */
public class DaoFactory {
	/**
	 * get Dao instance according to selected type
	 * <ul>
	 * <li>{@link Constants#DAO_TYPE_INMEMORY} For Inmemory Dao</li>
	 * <li>{@link Constants#DAO_TYPE_HAZZEL_CAST} For HazzelCase Dao</li>
	 * </ul>
	 * 
	 * @param daoType
	 * @return
	 * @throws BusinessException
	 */
	public static EmployeeDao getEmployeeDaoInstance(int daoType) throws BusinessException {

		if (daoType == Constants.DAO_TYPE_INMEMORY) {
			return EmployeeDaoInmemoryImpl.instance();
		} else if (daoType == Constants.DAO_TYPE_HAZZEL_CAST) {
			return EmployeeDaoHazelcastDaoImpl.instance();
		} else {
			throw new BusinessException("illegale dao type");
		}
	}

}
