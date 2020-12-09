import com.moserstefan.envdata.EnvData;
import com.moserstefan.envdata.IEnvService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class DateClient {
    public static void main(String[] args){
        try{
            String adr = "DateService";
            Registry reg = LocateRegistry.getRegistry();
            IEnvService serviceobject = (IEnvService) reg.lookup(adr);
            testApi(serviceobject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void testApi(IEnvService service) {
        try {
            while (true) {
                String[] sensors = service.requestEnvironmentDataTypes();
                for (String sensor : sensors) {
                    EnvData dataO = service.requestEnvironmentData(sensor);
                    System.out.print(dataO);
                    System.out.println();
                    System.out.println("*****************************");
                }// for sensor
                System.out.println();
                System.out.println();
                EnvData[] dataOs = service.requestAll();
                for (EnvData dataO : dataOs) {
                    System.out.println(dataO);
                } // for data
                System.out.println();
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (Exception _e) {
                    _e.printStackTrace();
                }
            }
        } catch (RemoteException _e) {
            System.out.println("There was an error while communicating with the server");
        }
    }
}
