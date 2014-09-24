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
 * MODULE   | public class HJ2PropertiesFactory extends Object          *
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

public class HJ2PropertiesFactory extends Object {
    private static HJ2Properties m_objHJ2Properties= null;
	
    public static HJ2Properties getInstance() {
        if( m_objHJ2Properties == null )
            m_objHJ2Properties= new HJ2Properties();
        return( m_objHJ2Properties );	
    }
}
    

