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
 * MODULE   | public class HJ2AppHelper extends Object 			*
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
 * 04-2007: Replace white spaces with "-" from application name.	*
 *									*
 *									*
 *									*
 *----------------------------------------------------------------------*
 * NOTES 	   							*
 *----------------------------------------------------------------------*
 * - La clase HJ2AppHelper obtiene el nombre de la applicacion J2EE que *
 * la haya instanciado en el contenedor de WebSphere 5.X o superior.	*
 * Para la obtencion del nombre de la aplicacion se utiliza una tecnica	*
 * indirecta basada en los hashCode de los classloaders utilizados y	*
 * luego comparandoles con aquellos obtenidos para la lista de las 	*
 * aplicaciones desplegadas.						*
 * - Se utiliza el siguiente metodo:					*
 * (1) getClassLoader().hashCode(); para obtener al hashCode del	*
 * classloader actual.							*
 * (2) Luego se compara (1) con aquellos hashCode obtenidos con		*
 * getDeployedApplication().getClassLoader().hashCode(); y cuando haya	*
 * una coincidencia sabemos a que aplicacion se corresponde el		* 
 * classloader.								*
 * (3)Se invoca webGroup.getApplicationName(); para conseguir el String	*
 * con el nombre del la aplicacion.					*
 * 									*
 *	- ClassLoaders de las applicaciones de empresa WebSphere	*											
 * Con respecto a los classloaders existen dos casos distintos a tener	*
 * en cuenta de acuerdo con la configuracion de la aplicacion en 	*
 * WebSphere:								*
 * 									*
 * (a) WAR Classloader Policy Module					*
 * 									*
 * 			J2EE Application				*
 * 				-> classloader 				*
 * 						-> EJBs			*
 * 						-> Resource adapters	*
 * 				-> WebApplication - 1			*
 * 						-> classloader		*
 * 				-> WebApplication - n			*
 * 						-> classloader		*
 * 									*
 * (b) WAR Classloader Policy Application				*
 * 									*
 * 			J2EE Application				*
 * 				-> classloader 				*
 * 						-> EJBs			*
 * 						-> Resource adapters	*
 * 						-> WebApplication - 1	*
 *						-> WebApplication - n	*
 *									*
 *- No se contempla el caso "Application classloader policy: SINGLE"	*
 *									*
 *----------------------------------------------------------------------*/
package org.holistic.j2containerhelper;

import java.util.Enumeration;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.ibm.ws.webcontainer.srt.WebGroup;
import com.ibm.ws.webcontainer.WebContainer;
import com.ibm.ws.runtime.deploy.DeployedModule;
import com.ibm.ws.webcontainer.exception.WebAppNotFoundException;


/*
 * @author HolisticView
 * @version 1.0
 * 
 * 
 */
public class HJ2AppHelper extends Object {
	private final static String _DEFAULT_APPNAME= "default-logger";
	

	private static long get_current() {
		return( Thread.currentThread().getClass().getClassLoader().hashCode() );
	}
	
	private static long get_parent() {
		return( Thread.currentThread().getClass().getClassLoader().getParent().hashCode() );
	}
	
	private static long get_threadcontext() {
		return( Thread.currentThread().getContextClassLoader().hashCode() );
	}
	
	public static String get_AppName() {
		String m_strApplication= null;

		HJ2AppList m_objAppList= HJ2AppListFactory.getInstance();
		if( m_objAppList == null ) return ( _DEFAULT_APPNAME );
		
		if( (m_strApplication= m_objAppList.get( get_threadcontext() )) != null )
			return( m_strApplication.replaceAll(" ","-") );

		if( (m_strApplication= m_objAppList.get( get_current() )) != null )
			return( m_strApplication.replaceAll(" ","-") );
						
		if( (m_strApplication= m_objAppList.get( get_parent() )) != null )
			return( m_strApplication.replaceAll(" ","-") );			
			
		return ( _DEFAULT_APPNAME );			
	}
	
	

}
