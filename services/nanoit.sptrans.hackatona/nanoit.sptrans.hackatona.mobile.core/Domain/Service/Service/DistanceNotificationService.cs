using nanoit.sptrans.hackatona.core.domain.entity;
using nanoit.sptrans.hackatona.mobile.core.Domain.Entity;
using nanoit.sptrans.hackatona.mobile.core.Infra.Repository.Implements;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace nanoit.sptrans.hackatona.mobile.core.Domain.Service.Service
{
    public class DistanceNotificationService
    {
        public BusStopDistanceReturn GetDistance(List<BusPosition> busPositions, int olhoVivoId, int idStop)
        {
            double aux = double.MaxValue;
            BusStopDistance busStopDistance = null;
            BusStopDistanceReturn result = new BusStopDistanceReturn();
            result.Distance = double.MaxValue;

            DistanceNotificationRepository repository = new DistanceNotificationRepository();

            foreach (var item in busPositions)
            {
                busStopDistance = repository.GetDistance(olhoVivoId, idStop, double.Parse(item.YPosition.ToString().Replace(",", "")), double.Parse(item.XPosition.ToString().Replace(",", "")), int.Parse(item.Bus));

                if (busStopDistance.Status == 0)
                    continue;

                aux = busStopDistance.Distance;

                if (aux < result.Distance)
                {
                    result.Distance = aux;
                    result.OlhoVivoId = olhoVivoId;
                    result.BusPrefix = int.Parse(item.Bus);
                    result.NameSource = busStopDistance.NameSource;
                    result.NameTarget = busStopDistance.NameTarget;
                    result.Prefix = busStopDistance.Prefix;
                    result.PrefixType = busStopDistance.PrefixType;
                    result.BusType = busStopDistance.BusType;
                }
            }

            return result;
        }
    }
}
