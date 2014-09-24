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
 * MODULE   | public class HJ2Properties                                *
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
package org.holistic.j2containerhelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HJ2Properties {
    private static String	_PROPERTIES=    "ws-helper.properties";
    private static String	_P_INTER=       "check-interval";
    private static String	_P_DEBUG=       "debug-enabled";
    private static long         _V_INTER= 	5; // Minutes
    private static boolean      _V_DEBUG= 	false;
    
    private long		m_lInterval= 	0;
    private boolean		m_boolDebug= 	false;
    
    public HJ2Properties() {
        get_properties();
    }
    
    private void get_properties() {
        InputStream m_objInput= null;
	
        set_interval( _V_INTER );
        set_debug( _V_DEBUG );
		
	m_objInput= getClass().getClassLoader().getResourceAsStream( _PROPERTIES );
        if ( m_objInput == null) m_objInput= Thread.currentThread().getContextClassLoader().getResourceAsStream( _PROPERTIES );
        
	if( m_objInput != null )
	{
            StderrDebug.fatal( "get_properties() loading properties" );
            Properties m_objProperties= new Properties();
            try {
                    m_objProperties.load( m_objInput );
                    String m_strInterval= m_objProperties.getProperty( _P_INTER );
                    if( m_strInterval != null )
                        set_interval( Long.parseLong(  m_strInterval ) );
                    
                    String m_strDebug= m_objProperties.getProperty( _P_DEBUG );
                    if(  m_strDebug != null ) 
                        set_debug( m_strDebug );
            }
            catch( IOException e ) {
                StderrDebug.fatal( "get_properties() exception: " + e.getMessage() );
                ;
            }
        }
        else {
            StderrDebug.fatal( "get_properties() file not found: " + _PROPERTIES );
        }
    }
	
    public void set_interval( long lValue ) {
        if( lValue != -1 )
            m_lInterval= lValue * 60000;
        else	
            m_lInterval= -1;
    }
	
    public long get_interval(  ) {
        return( m_lInterval );
    }
    
    public void set_debug( boolean boolValue ) {
        m_boolDebug= boolValue;
    }
    
    public void set_debug( String strValue ) {
        m_boolDebug= Boolean.valueOf(strValue).booleanValue();
    }
    
    public boolean get_debug(  ) {
        return( m_boolDebug );
    }
}
