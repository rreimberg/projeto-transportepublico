using System.Collections.Generic;
using System.Linq;
using nanoit.sptrans.hackatona.core.domain.entity;
using nanoit.sptrans.hackatona.core.Infra.Repository.Implements;

namespace nanoit.sptrans.hackatona.core.application
{
    public class StopApplication
    {
        public IEnumerable<StopReturn> GetStops(string latitude,string longitude,int direction,int distanceMax)
        {
                using (var hibernateRepository = new StopRepository())
                {
                    var application = hibernateRepository.FindStopByCoordinates(latitude, longitude, direction, distanceMax);
                    return application;
                }
        }

        public IEnumerable<StopLineReturn> GetLineByStop(int stopId)
        {
            using (var nhibernateRepostiroy = new StopLineRepository())
            {
                var result = nhibernateRepostiroy.FindByStop(stopId);

                return result;
            }
        }

        public IEnumerable<StopReturn> GetStopByLine(string prefix)
        {
            using (var nhibernateRepostiroy = new StopLineRepository())
            {
                var result = nhibernateRepostiroy.FindMany(x => x.Id.Prefix == prefix, l => l.Id.Stop);


                List<StopReturn> stops = new List<StopReturn>();

                foreach (var item in result)
                {
                    stops.Add(new StopReturn()
                    {
                        Id = item.Id.Stop.Id,
                        Latitude = item.Id.Stop.Latitude,
                        Longitude = item.Id.Stop.Longitude,
                        Name = item.Id.Stop.Name
                    });
                }

                return stops;
            }
        }

        public void GetLines()
        {

        }
    }
}
