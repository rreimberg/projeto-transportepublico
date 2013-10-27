using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using nanoit.sptrans.hackatona.core.domain.entity;
using NHibernate.Criterion;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Implements
{
    public class ItineraryRepository : HibernateRepository<Itinerary>
    {
        public ItineraryRepository()
            : base(new HibernateConnection())
        {
        }

        public IEnumerable<Itinerary> FindManyItinerary(string pSPTransId)
        {
            return this.Connection.Connection.CreateCriteria<Itinerary>()
                .CreateAlias("Line", "Line")
                .Add(
                    Restrictions.Eq("Line.OlhoVivoId", pSPTransId)).List<Itinerary>();
        }
    }
}
