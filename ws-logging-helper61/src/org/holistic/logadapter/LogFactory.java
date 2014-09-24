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
 * PACKAGE  | package org.holistic.logadapter;                          *
 *----------+-----------------------------------------------------------*
 * MODULE   | public class LogFactory extends LogFactoryImpl            *
 *----------+-----------------------------------------------------------*
 *									*
 *									*
 *----------------------------------------------------------------------*
 * CREATED	    							*
 *----------------------------------------------------------------------*
 * AGARCIA/04-2007							*
 *									*
 *									*
 *----------------------------------------------------------------------*
 * MODIFIED 	   							*
 *----------------------------------------------------------------------*
 * 									*
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
package org.holistic.logadapter;

import org.holistic.j2containerhelper.HJ2AppHelper;
import org.holistic.j2containerhelper.StderrDebug;

public class LogFactory extends org.apache.commons.logging.impl.LogFactoryImpl {
	
	public org.apache.commons.logging.Log getInstance(Class clazz) throws org.apache.commons.logging.LogConfigurationException {
                String m_strAppName;
                m_strAppName= HJ2AppHelper.get_AppName();
                StderrDebug.trace( "getInstance(Class clazz) req/real logger: " + clazz.getName() + "/" + m_strAppName );
		return( super.getInstance( m_strAppName ) );
	}
				
	public org.apache.commons.logging.Log getInstance(String name) throws org.apache.commons.logging.LogConfigurationException {
                String m_strAppName;
                m_strAppName= HJ2AppHelper.get_AppName();
                StderrDebug.trace( "getInstance(String name) req/real logger: " + name + "/" + m_strAppName );
		return( super.getInstance( m_strAppName ) );
	}

}
