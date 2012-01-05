/*
 * Copyright  2003-2011 The Apache Software Foundation and others.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.eclipse.stardust.modeling.integration.webservices.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Properties;

import org.eclipse.stardust.common.Base64;
import org.xml.sax.InputSource;

/**
 * Utility class for retrieving WSDL files from a URI.
 * <p>
 * Initially derived from {@link org.apache.axis.utils.XMLUtils} from Apache Axis (see
 * http://svn.apache.org/viewvc/axis/axis1/java/trunk/src/org/apache/axis/utils/XMLUtils.java)
 * <p>
 * Modifications include:
 *   o added support for HTTP proxy authentication when retrieving WSDLs
 *   o added support for arbitrary client provided request properties
 *
 * @author florin.herinean
 *
 */
public class WsdlUtils
{
   private static final String httpAuthCharEncoding = "ISO-8859-1"; //$NON-NLS-1$

   /**
    * Method was copied from
    * {@link org.apache.axis.utils.XMLUtils#getInputSourceFromURI(String, String, String)}
    * and slightly adjusted for more flexibility.
    */
   public static InputSource getInputSourceFromURI(String uri, String username,
         String password, Properties customProperties)
      throws IOException, ProtocolException, UnsupportedEncodingException
   {
      URL wsdlurl = null;
      try
      {
         wsdlurl = new URL(uri);
      }
      catch (MalformedURLException e)
      {
         // we can't process it, it might be a 'simple' foo.wsdl
         // let InputSource deal with it
         return new InputSource(uri);
      }

      // if this is not an HTTP{S} url, let InputSource deal with it
      if (!wsdlurl.getProtocol().startsWith("http")) //$NON-NLS-1$
      {
         return new InputSource(uri);
      }

      // if no authentication, just let InputSource deal with it
      if (getProxyAuth() == null && username == null && wsdlurl.getUserInfo() == null
            && (customProperties == null || customProperties.isEmpty()))
      {
         return new InputSource(uri);
      }

      URLConnection connection = wsdlurl.openConnection();
      // Does this work for https???
      if (!(connection instanceof HttpURLConnection))
      {
         // can't do http with this URL, let InputSource deal with it
         return new InputSource(uri);
      }
      HttpURLConnection uconn = (HttpURLConnection) connection;
      String userinfo = wsdlurl.getUserInfo();
      uconn.setRequestMethod("GET"); //$NON-NLS-1$
      uconn.setAllowUserInteraction(false);
      uconn.setDefaultUseCaches(false);
      uconn.setDoInput(true);
      uconn.setDoOutput(false);
      uconn.setInstanceFollowRedirects(true);
      uconn.setUseCaches(false);

      // username/password info in the URL overrides passed in values
      String auth = null;
      if (userinfo != null)
      {
         auth = userinfo;
      }
      else if (username != null)
      {
         auth = (password == null) ? username : username + ":" + password; //$NON-NLS-1$
      }

      if (auth != null)
      {
         String authorization = "Basic " + WsdlUtils.base64encode(auth.getBytes(WsdlUtils.httpAuthCharEncoding)); //$NON-NLS-1$
         uconn.setRequestProperty("Authorization", authorization); //$NON-NLS-1$
      }

      // TODO: no longer necessary with jdk 5.0 ?
      if (getProxyAuth() != null)
      {
         String proxyAuthorization = "Basic " + WsdlUtils.base64encode(getProxyAuth().getBytes(WsdlUtils.httpAuthCharEncoding)); //$NON-NLS-1$
         uconn.setRequestProperty("Proxy-Authorization", proxyAuthorization); //$NON-NLS-1$
      }

      if (customProperties != null)
      {
         Iterator<?> keys = customProperties.keySet().iterator();
         for (; keys.hasNext();)
         {
            String key = (String) keys.next();
            String value = customProperties.getProperty(key);
            uconn.setRequestProperty(key, value);
         }
      }
      uconn.connect();
      return new InputSource(uconn.getInputStream());
   }

   private static String getProxyAuth()
   {
      String proxyUser = System.getProperty("http.proxyUser"); //$NON-NLS-1$
      String proxyPassword = System.getProperty("http.proxyPassword"); //$NON-NLS-1$
      return proxyUser == null ? null
            : proxyPassword == null ? proxyUser : proxyUser + ":" + proxyPassword; //$NON-NLS-1$
   }

   public static String base64encode(byte[] bytes)
   {
      return new String(Base64.encode(bytes));
   }

}
