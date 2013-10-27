using System.Collections.Generic;
using NHibernate.Transform;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Implements
{
    public class StopRepository : HibernateRepository<Stop>
    {
        public StopRepository(): base(new HibernateConnection())
        {
        }

        public IEnumerable<StopReturn> FindStopByCoordinates(string latitude,string longitude,int direction,int distanceMax)
        {
            return Connection.Connection.CreateSQLQuery("exec dbo.prPesquisaParadaPorCoordenada :latitude, :longitude, :direction, :distanceMax")
                .SetString("latitude", latitude)
                .SetString("longitude", longitude)
                .SetInt32("direction", direction)
                .SetInt32("distanceMax", distanceMax)
                .SetResultTransformer(new AliasToBeanResultTransformer(typeof(StopReturn))).List<StopReturn>();
        }
    }
}
