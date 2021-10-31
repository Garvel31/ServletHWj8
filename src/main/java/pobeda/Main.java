package pobeda;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.webapp.*;



public class Main {


    public static void main(String[] args) throws Exception {


        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) webPort = "8099";
        Server server = new Server(Integer.valueOf(webPort));
        String webappDir = "src/main/webapp/";
        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setResourceBase(webappDir);
        context.setDescriptor(webappDir + "WEB-INF/web.xml");
        context.setConfigurations(new Configuration[]{
                new AnnotationConfiguration(), new WebXmlConfiguration(),
                new WebInfConfiguration(),
                new PlusConfiguration(), new MetaInfConfiguration(),
                new FragmentConfiguration(), new EnvConfiguration()});
        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.start();
        server.dump(System.err);
        server.join();


    }


}