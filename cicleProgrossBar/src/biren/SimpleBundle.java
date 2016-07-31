package biren;
 

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SimpleBundle implements BundleActivator
{
    public void start(BundleContext context) throws Exception
    {
        System.err.println("你好我是Service demo插件,我已经启动了 我的BundleId为："+context.getBundle().getBundleId());
   
    }
   
    public void stop(BundleContext context)
    {
    	System.err.println("你好我是ehl视频插件,我被停止�? 我的BundleId为："+context.getBundle().getBundleId());


    }

}
