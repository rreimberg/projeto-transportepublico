using nanoit.sptrans.hackatona.core.domain.entity;
using nanoit.sptrans.hackatona.core.Infra.Repository;
using nanoit.sptrans.hackatona.mobile.core.Domain.Entity;
using NHibernate.Transform;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace nanoit.sptrans.hackatona.mobile.core.Infra.Repository.Implements
{
    public class DistanceNotificationRepository : HibernateRepository<BusPosition>
    {
        public DistanceNotificationRepository()
            : base (new HibernateConnection())
        {
        }

        public BusStopDistance GetDistance(int olhoVivoId, int stopId, double latitude, double longitude, int busId)
        {
            return Connection.Connection.CreateSQLQuery("exec prCalcularDistanciaEntreOnibuseParada :OnibusLatitude, :OnibusLongitude, :OlhoVivoId, :ParadaDestinoId, :OnibusId")
                .SetDouble("OnibusLatitude", latitude)
                .SetDouble("OnibusLongitude", longitude)
                .SetInt32("OlhoVivoId", olhoVivoId)
                .SetInt32("ParadaDestinoId", stopId)
                .SetInt32("OnibusId", busId)
                .SetResultTransformer(new AliasToBeanResultTransformer(typeof(BusStopDistance)))
                .UniqueResult<BusStopDistance>();
        }
    }
}
