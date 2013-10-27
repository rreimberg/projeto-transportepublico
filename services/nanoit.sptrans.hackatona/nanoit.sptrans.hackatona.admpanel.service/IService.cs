using nanoit.sptrans.hackatona.admpanel.core.Domain.Entity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace nanoit.sptrans.hackatona.admpanel.service
{
    [ServiceContract]
    public interface IService
    {

        List<ReasonType> ListReasonsType();

        
        List<BusTraffic> FindCrowdHistoryByTravelId(int travelId);
        
        List<BusTraffic> FindTrafficHistoryByTravelId(int travelId);

        
        List<BusLine> FindBusLineByPrefix(string prefix);

        List<BusConfiguration> FindBusLineConfigurations(string prefix);
        


        ServiceResponse StartTravel(string linePrefix,
                                    string busPrefix,                        
                                    int direction,
                                    int bustRollCounter,
                                    int busCollectorId,
                                    int busDriverId,
                                    int longitude,
                                    int latitude,
                                    DateTime timeStamp,
                                    String token);


        ServiceResponse EndTravel(int travelId,
                                  int bustRollCounter,
                                  int longitude,
                                  int latitude,
                                  DateTime timeStamp,
                                  String token);


        ServiceResponse AbortTravel(int travelId,
                                    int bustRollCounter,
                                    int reasonId,
                                    string reasonDescription,
                                    int longitude,
                                    int latitude,
                                    DateTime timeStamp,
                                    String token);


        ServiceResponse NotifyTraffic(int trafficId,
                                      int longitude,
                                      int latitude,
                                      DateTime timeStamp,
                                      String token);


        ServiceResponse NotifyCrowd(int travelId,
                                    int crowdId,
                                    int longitude,
                                    int latitude,
                                    DateTime timeStamp,
                                    String token);


        ServiceResponse NotifyWarning(int travelId,
                                      int warningId,
                                      string warningDescription,
                                      int longitude,
                                      int latitude,
                                      DateTime timeStamp,                          
                                      String token);


        ServiceResponse ReportIncorrectNotification(int travelId, 
                                                    int warningId);


    }
}
