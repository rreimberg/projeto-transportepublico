using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace nanoit.sptrans.hackatona.admpanel.service
{
    public class Service1 : IService
    {
        public List<core.Domain.Entity.ReasonType> ListReasonsType()
        {
            throw new NotImplementedException();
        }

        public List<core.Domain.Entity.BusTraffic> FindCrowdHistoryByTravelId(int travelId)
        {
            throw new NotImplementedException();
        }

        public List<core.Domain.Entity.BusTraffic> FindTrafficHistoryByTravelId(int travelId)
        {
            throw new NotImplementedException();
        }

        public List<core.Domain.Entity.BusLine> FindBusLineByPrefix(string prefix)
        {
            throw new NotImplementedException();
        }

        public List<core.Domain.Entity.BusConfiguration> FindBusLineConfigurations(string prefix)
        {
            throw new NotImplementedException();
        }

        public core.Domain.Entity.ServiceResponse StartTravel(string linePrefix, string busPrefix, int direction, int bustRollCounter, int busCollectorId, int busDriverId, int longitude, int latitude, DateTime timeStamp, string token)
        {
            throw new NotImplementedException();
        }

        public core.Domain.Entity.ServiceResponse EndTravel(int travelId, int bustRollCounter, int longitude, int latitude, DateTime timeStamp, string token)
        {
            throw new NotImplementedException();
        }

        public core.Domain.Entity.ServiceResponse AbortTravel(int travelId, int bustRollCounter, int reasonId, string reasonDescription, int longitude, int latitude, DateTime timeStamp, string token)
        {
            throw new NotImplementedException();
        }

        public core.Domain.Entity.ServiceResponse NotifyTraffic(int trafficId, int longitude, int latitude, DateTime timeStamp, string token)
        {
            throw new NotImplementedException();
        }

        public core.Domain.Entity.ServiceResponse NotifyCrowd(int travelId, int crowdId, int longitude, int latitude, DateTime timeStamp, string token)
        {
            throw new NotImplementedException();
        }

        public core.Domain.Entity.ServiceResponse NotifyWarning(int travelId, int warningId, string warningDescription, int longitude, int latitude, DateTime timeStamp, string token)
        {
            throw new NotImplementedException();
        }

        public core.Domain.Entity.ServiceResponse ReportIncorrectNotification(int travelId, int warningId)
        {
            throw new NotImplementedException();
        }
    }
}
