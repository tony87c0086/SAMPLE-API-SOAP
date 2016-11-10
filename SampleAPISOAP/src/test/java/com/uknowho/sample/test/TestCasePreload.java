package com.uknowho.sample.test;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * This BaseTestPreload class provides test cases initialization for test cases.
 * 
 * Created date <21-Jul-2016>
 * 	
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Ignore
@ContextConfiguration(
		locations = {"classpath:/config/applicationDataSource.xml",
				"classpath:/config/applicationHibernateContext.xml",
				"classpath:/config/applicationContextServices.xml",
				"classpath:/config/applicationContext.xml"})
public class TestCasePreload extends AbstractTransactionalJUnit4SpringContextTests {

	private static final Logger logger = LoggerFactory.getLogger(TestCasePreload.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected ResourceBundle rb;
	
	public TestCasePreload() {
		String className = this.getClass().getName();
		try {
			rb = ResourceBundle.getBundle(className);
		} catch (MissingResourceException mre) {
            logger.trace("No resource bundle found for: " + className);
        }
	}
	
    protected Object populate(final Object obj) throws Exception {
        // loop through all the beans methods and set its properties from its .properties file
        Map<String, String> map = new HashMap<String, String>();

        for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();) {
            String key = keys.nextElement();
            map.put(key, rb.getString(key));
        }

        BeanUtils.copyProperties(obj, map);

        return obj;
    }
    
    /**
     * Create a HibernateTemplate from the SessionFactory and call flush() and clear() on it.
     * Designed to be used after "save" methods in tests: http://issues.appfuse.org/browse/APF-178.
     *
     * @throws org.springframework.beans.BeansException
     *          when can't find 'sessionFactory' bean
     */
 
    protected void flush() throws BeansException {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.flush();
    }

    /**
     * Flush search indexes, to be done after a reindex() or reindexAll() operation
     */
 
    public void flushSearchIndexes() {
        Session currentSession = sessionFactory.getCurrentSession();
        final FullTextSession fullTextSession = Search.getFullTextSession(currentSession);
        fullTextSession.flushToIndexes();
    }
    
}
