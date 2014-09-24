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
 *									*
 *----------------------------------------------------------------------*
 * CHANGE LOG    							*
 *----------------------------------------------------------------------*
 *									*
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

import com.ibm.ws.webcontainer.srt.WebGroup;
import com.ibm.ws.webcontainer.WebContainer;
import com.ibm.ws.runtime.deploy.DeployedModule;
import com.ibm.ws.runtime.service.ApplicationServer;
import com.ibm.ws.webcontainer.exception.WebAppNotFoundException;


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
		WebContainer 	m_objContainer= WebContainer.getWebContainer();
		Enumeration 	m_objElement= m_objContainer.getWebGroupNames();
		
		while( m_objElement.hasMoreElements() )
		{
			m_objElement.nextElement();
			m_iCount++;
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
	
	private synchronized void populate() {
		long			m_lHashCode;
		String 			m_strMyAppName= null;
		String 			m_strName;
		
		WebContainer 	m_objContainer= WebContainer.getWebContainer();
		Enumeration 	m_objElement= m_objContainer.getWebGroupNames();
		DeployedModule 	m_objModule;
		WebGroup 		m_objWebGroup;
	
                StderrDebug.trace( "Invoking populate()" );
                
		if ( count() == appsize() )	return;
				
		container().clear();
		timestamp( System.currentTimeMillis() );
		
		while( m_objElement.hasMoreElements() )
		{
			m_strName= m_objElement.nextElement().toString();
			
			try {
					m_objModule= m_objContainer.getWebModuleConfig( m_strName );
					m_lHashCode= m_objModule.getDeployedApplication().getClassLoader().hashCode();
		
					add( m_lHashCode, m_objModule.getDeployedApplication().getName() );
					add( m_objModule.getClassLoader().hashCode(), m_objModule.getDeployedApplication().getName() );

			} catch (WebAppNotFoundException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		}
	}

}
