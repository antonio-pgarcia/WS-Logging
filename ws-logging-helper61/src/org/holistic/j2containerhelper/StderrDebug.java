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
 * MODULE   | public class StderrDebug                                  *
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

import com.ibm.websphere.management.configservice.SystemAttributes;
import com.ibm.websphere.models.config.serverindex.ServerIndex;
import com.ibm.ws.runtime.deploy.DeployedApplication;
import com.ibm.ws.runtime.deploy.DeployedModule;






public class StderrDebug {
    private static String _LOGADAPTER= "(WS-LOGSUBSYS) ";
    public StderrDebug() {
    }
    
    public static void trace(Object objValue) {
        if( HJ2PropertiesFactory.getInstance().get_debug() ) {
            System.err.println( _LOGADAPTER + objValue );
        }
    }
    
    public static void fatal(Object objValue) {
            System.err.println( _LOGADAPTER + objValue );
    }

}
