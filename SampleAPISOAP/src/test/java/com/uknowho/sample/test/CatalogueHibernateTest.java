package com.uknowho.sample.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.uknowho.sample.soap.dao.CatalogueDao;
import com.uknowho.sample.soap.entity.Catalogue;

/**
 * This CatalogueTest class provides test cases for Catalogue related DAO test. 
 * 
 * Created date <12-Sep-2016>
 * 	
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class CatalogueHibernateTest extends TestCasePreload {
	
	@Autowired
	private CatalogueDao CatalogueDao;
	
	@Test
	public void testCatalogueDao() {
		Catalogue Catalogue = null;
		Integer Catalogueid = null;
		List<Catalogue> cataloguelist = CatalogueDao.list();
		
		if (cataloguelist != null && cataloguelist.size()>0) {
			Catalogueid = cataloguelist.get(0).getCatalogueID();
			Catalogue = CatalogueDao.get(Catalogueid);
		} else {
			Catalogue = new Catalogue();
		}
		assertNotNull(Catalogue);
	}
	
}
