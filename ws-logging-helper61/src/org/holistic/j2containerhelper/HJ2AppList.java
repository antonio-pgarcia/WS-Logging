/*----------------------------------------------------------------------*
 * HolisticView/MetaKnowledge						*
 *     									*
 *									*
 * Copyright Notice:                                                	*
 * Free use of this library is permitted under the guidelines and   	*
 * in accordance with the most current version of the Common Public 	*
 * License.                                                         	*
 * http://www.opensource.org/licenses/cpl.php                       	*
 *									*
 *									*
 *									*
 *----------+-----------------------------------------------------------*
 * PACKAGE  | package org.holistic.j2containerhelper; 			*
 *----------+-----------------------------------------------------------*
 * MODULE   | public class HJ2AppList extends Object 			*
 *----------+-----------------------------------------------------------*
 *									*
 *									*
 *----------------------------------------------------------------------*
 * CREATED	    							*
 *----------------------------------------------------------------------*
 * AGARCIA/09-2006							*
 *									*
 *									*
 *----------------------------------------------------------------------*
 * MODIFIED 	   							*
 *----------------------------------------------------------------------*
 * AGARCIA/04-2007							*
 * AGARCIA/11-2007							*
 * 									*
 *----------------------------------------------------------------------*
 * CHANGE LOG    							*
 *----------------------------------------------------------------------*
 * AGARCIA/11-2007: WebSphere Version 6.1 Port				*
 *									*
 *									*
 *									*
 *----------------------------------------------------------------------*
 * NOTES 	   							*
 *----------------------------------------------------------------------*
 *									*
 *									*
 *									*
 *									*
 *									*
 *									*
 *----------------------------------------------------------------------*/
package org.holistic.j2containerhelper;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;
import java.lang.reflect.Field;

import com.ibm.ws.runtime.component.ApplicationMgrImpl;
import com.ibm.wsspi.runtime.service.WsServiceRegistry;
import com.ibm.ws.runtime.component.DeployedApplicationImpl;

public class HJ2AppList extends Object 
{
	private long 		m_lTimeStamp= 	0;
	private Hashtable 	m_objContainer= null;
	
	public HJ2AppList() {
		container( new Hashtable() );
                HJ2PropertiesFactory.getInstance();
		populate();
	} 
	
	private void timestamp( long lValue ) {
		m_lTimeStamp= lValue;	
	}
	
	private long timestamp( ) {
		return( m_lTimeStamp );
	}
	
	private long currenttime() {
		return( System.currentTimeMillis() );
	}
	
	private void container( Hashtable objValue ) {
		m_objContainer= objValue;
	}
	
	private Hashtable container(  ) {
		return(	m_objContainer );
	}
	
	private void add( long lValue, String strValue ) {
		container().put(Long.toHexString(lValue), strValue );
	}
	
	public String get( long lValue ) {
		String m_strKey= Long.toHexString( lValue );
		repopulate();
                
		if( container().containsKey( m_strKey ) )
			return( (String) container().get( m_strKey ) );
		else 
			return( null );
	}
	
	private int count() {
		return( container().size() );
	}
	
	private int appsize() {
		int		m_iCount= 0;
                
                try {
                        m_iCount= getDeployedApplications().size();
                    } catch (Exception ex) {
                        StderrDebug.fatal("Could not determine appsize: " + ex.getMessage());
                    }
		return( m_iCount );
	}
	
	private void repopulate()
	{
		if( HJ2PropertiesFactory.getInstance().get_interval() == -1 )
			return;
			
		if( ( currenttime() - timestamp() ) >= HJ2PropertiesFactory.getInstance().get_interval() )
			if ( count() != appsize() )
				populate();
	}
	
        
        private ArrayList getDeployedApplications() throws Exception {
            Field                   m_objDeployedApplications;
            ArrayList               m_alDeployedApplications;
            ApplicationMgrImpl      m_objAppMgr;
            
            m_objAppMgr= (ApplicationMgrImpl ) WsServiceRegistry.getService(this, com.ibm.ws.runtime.service.ApplicationMgr.class);
            m_objDeployedApplications= ApplicationMgrImpl.class.getDeclaredField("deployedApplications");
            m_objDeployedApplications.setAccessible(true);
            m_alDeployedApplications= (ArrayList) m_objDeployedApplications.get(m_objAppMgr);
            
            return( m_alDeployedApplications );
        }
        
	private synchronized void populate() {
                ArrayList               m_alDeployedApplications;
                DeployedApplicationImpl m_objDeployedApplication;
                
                try {
                        StderrDebug.trace( "Invoking populate()" );
                        m_alDeployedApplications= getDeployedApplications();

                        if ( count() == appsize() )	return;
                        container().clear();
                        timestamp( System.currentTimeMillis() );
                        for( int m_iCount= 0; m_iCount< m_alDeployedApplications.size(); m_iCount++ ) {
                            m_objDeployedApplication = (DeployedApplicationImpl) m_alDeployedApplications.get(m_iCount);
                            add( m_objDeployedApplication.getClassLoader().hashCode(),m_objDeployedApplication.getName() );
                            StderrDebug.trace( "DeployedApplication: (" + m_objDeployedApplication.getName() + ") ClassLoader Hashcode (" + m_objDeployedApplication.getClassLoader().hashCode() + ")");
                        }
                } catch (Exception ex) {
                    StderrDebug.fatal(ex.getMessage());
                }
                
        }
}
