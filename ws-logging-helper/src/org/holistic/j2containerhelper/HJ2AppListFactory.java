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
 * MODULE   | public class HJ2AppListFactory extends Object             *
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

public class HJ2AppListFactory extends Object
{
	private static HJ2AppList m_objHJ2AppList= null;
	
	public static HJ2AppList getInstance() {
		if( m_objHJ2AppList == null )
			m_objHJ2AppList= new HJ2AppList();
		
		return( m_objHJ2AppList );	
	}

}
