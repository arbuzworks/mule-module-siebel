package org.mule.module.siebel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.tools.cloudconnect.annotations.Connector;
import org.mule.tools.cloudconnect.annotations.Operation;
import org.mule.tools.cloudconnect.annotations.Property;

import com.siebel.data.SiebelDataBean;
import com.siebel.data.SiebelException;
import com.siebel.data.SiebelPropertySet;
import com.siebel.data.SiebelService;

/**
 * A Connector for Siebel
 *
 */
@Connector(namespacePrefix="siebel")
public class SiebelCloudConnector implements Initialisable, Disposable
{
	private transient Log logger = LogFactory.getLog(getClass());
	public static final int DEFAULT_SIEBEL_PORT = 2321;
	private SiebelDataBean siebelDataBean;
	
	/**
	 * Siebel host
	 */
	@Property
    private String host;
	
	/**
	 * Siebel port
	 */
	@Property(optional=true)
    private String port = String.valueOf(DEFAULT_SIEBEL_PORT);
	
	/**
	 * Siebel path
	 */
	@Property
    private String path;
	
	/**
	 * Siebel user
	 */
	@Property
    private String user;
	
	/**
	 * Siebel password
	 */
	@Property
    private String password;
    
	/**
	 * Initialise SiebelCloudConnector, login to siebel server
	 */
	public void initialise() throws InitialisationException {
		try {
			int port = Integer.parseInt(this.port);

			String url = "siebel://" + host + ":" + port + path;

			siebelDataBean = new SiebelDataBean();

			siebelDataBean.login(url, user, password, "enu");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new InitialisationException(e, this);
		}
		logger.info("SiebelCloudConnector initialised");
	}

	/**
	 * Dispose SiebelCloudConnector, logoff siebel server
	 */
	public void dispose() {
		try {
			if (siebelDataBean != null)
				siebelDataBean.logoff();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("SiebelCloudConnector disposed");
	}
	
/**
 * Invoke siebel method 
 * @param service Name of siebel service
 * @param method Name of siebel method
 * @param propertySet Input siebel property set
 * @return Output siebel property set
 * @throws SiebelException
 */
    @Operation
    public SiebelPropertySet invokeMethod(String service, String method, SiebelPropertySet propertySet) throws SiebelException
    {
    	logger.debug("invoke service = " + service + ", method = " + method);
    	SiebelPropertySet propertySetOut = new SiebelPropertySet(); 
    	SiebelService siebelService = siebelDataBean.getService(service);
    	siebelService.invokeMethod(method, propertySet, propertySetOut);
    	siebelService.release();
    	
    	return propertySetOut;
    }

    /**
     * Get a siebel host
     * @return
     */
	public String getHost() {
		return host;
	}

	/**
	 * Specify the siebel host
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Get a siebel port
	 * @return
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Specify the siebel port
	 * @param port
	 */
	public void setPort(String portString) {
        try
        {
            int port = Integer.parseInt(portString);
            if (port < 1)
            {
                logger.warn("Negative value set for port in Siebel connector. Using default port: " + SiebelCloudConnector.DEFAULT_SIEBEL_PORT);
                port = SiebelCloudConnector.DEFAULT_SIEBEL_PORT;
            }
            this.port = String.valueOf(port);
        } catch (Exception e)
        {
            logger.error("Invalid value set for port in Siebel connector. Using default port: " + SiebelCloudConnector.DEFAULT_SIEBEL_PORT + ".\n" + e.getMessage(), e);
        }
	}

	/**
	 * Get a siebel path
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Specify the siebel path
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Get a siebel user
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Specify the siebel user
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Get a siebel password
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Specify the siebel password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
